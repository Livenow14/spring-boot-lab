package com.livenow.springmvc.mapping;

import com.livenow.springmvc.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/uri-pattern")
public class UriPatternController {


    @GetMapping("/users/{id}")
    public ResponseEntity<User> pathVariable(@PathVariable Long id) {
        User user = new User(id, "이름", "email");
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/patterns/{pattern:[a-z]}")
    public ResponseEntity<String> pattern(@PathVariable String pattern) {
        return ResponseEntity.ok().body("pattern");
    }

    /**
     * 이후의 모든 경로를 허용한다.
     * "*"이 한개일때는 추가 경로에 대해서는 실패한다
     * ex) patterns/* -> /patterns/aa/bb 살패, /patterns/aa 성공
     *  patterns/** -> /patterns/aa/bb 성공
     */
    @GetMapping("/patterns/**")
    public ResponseEntity<String> patternStars() {
        return ResponseEntity.ok().body("pattern-multi");
    }
}