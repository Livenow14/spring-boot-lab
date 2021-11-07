package com.livenow.springrestdocs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.operation.preprocess.OperationRequestPreprocessor;
import org.springframework.restdocs.operation.preprocess.OperationResponsePreprocessor;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;

@AutoConfigureRestDocs
public class ApiDocument {

    @Autowired
    protected MockMvc mockMvc;

    protected static RestDocumentationResultHandler toDocument(String title) {
        return document(title, getDocumentRequest(), preprocessResponse(prettyPrint()));
    }

    protected static OperationRequestPreprocessor getDocumentRequest() {
        return preprocessRequest(
                modifyUris()
                        .scheme("http")
                        .host("localhost")
                        .removePort(),
                prettyPrint());
    }

    protected static String toJson(Object object) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new IllegalStateException("직렬화 오류");
        }
    }
}
