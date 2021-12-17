package com.livenow.springmvc.mapping;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/param-header")
public class ParamHeaderController {

    @GetMapping(path = "/message")
    public ResponseEntity<String> message() {
        return ResponseEntity.ok().body("message");
    }

    /**
     * params 옵션에 "="는 name으로 들어오는 값이 hello인지 테스트합니다.
     * -> 만약 /param-header/message?name=hell 로 요청한다면, 요청이 실패합니다.
     * 공식 문서에서는 이를 통해 요청 매핑의 범위를 줄일 수 있다고 합니다.
     */
    @GetMapping(path = "/message", params = "name=hello")
    public ResponseEntity<String> messageForParam() {
        return ResponseEntity.ok().body("hello");
    }

    /**
     * HEADER로 지정된 헤더가 요청시 들어오는지 확인합니다.
     * params옵션과 동일하게, "="을 통해 요청 매핑의 범위를 줄일 수 있습니다.
     *
     * 또한, Content-Type 및 Accept를 헤더 조건과 일치시킬 수 있지만,
     * 이때는 consumes와 produces를 쓰는것이 더 좋다고 합니다.
     */
    @GetMapping(path = "/message", headers = "HEADER")
    public ResponseEntity<String> messageForHeader() {
        return ResponseEntity.ok().body("hi");
    }
}