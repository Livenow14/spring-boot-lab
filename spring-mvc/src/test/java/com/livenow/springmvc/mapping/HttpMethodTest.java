package com.livenow.springmvc.mapping;

import com.livenow.springmvc.domain.User;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.is;

@DisplayName("Http Method")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpMethodTest {

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    /**
     * HttpMethodController > createUser 메서드
     */
    @DisplayName("Http Method - POST")
    @Test
    void createUser() {
        User user = new User("이름", "email@email.com");

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(user)
                .when().post("/http-method/users")
                .then().log().all()
                .statusCode(HttpStatus.CREATED.value())
                .header("Location", "/users/1");
    }

    /**
     * HttpMethodController > showUser 메서드
     */
    @DisplayName("Http Method - GET")
    @Test
    void showUser() {
        RestAssured.given().log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/http-method/users")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body("size()", is(2));
    }


    /**
     * HttpMethodController > updateUser 메서드
     */
    @DisplayName("Http Method - PATCH")
    @Test
    void updateUser() {
        User user = new User("바뀐아룸");

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .body(user)
                .when().patch("/http-method/users/1")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body("name", is(user.getName()));
    }

    /**
     * HttpMethodController > changeeUser 메서드
     */
    @DisplayName("Http Method - PUT")
    @Test
    void changeUser() {
        User user = new User("바뀐아룸");

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .body(user)
                .when().put("/http-method/users/1")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body("name", is(user.getName()));
    }

    /**
     * HttpMethodController > deleteUser 메서드
     */
    @DisplayName("Http Method - DELETE")
    @Test
    void deleteUser() {
        RestAssured.given().log().all()
                .when().delete("/http-method/users/1")
                .then().log().all()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }
}
