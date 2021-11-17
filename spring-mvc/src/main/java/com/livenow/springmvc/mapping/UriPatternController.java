package com.livenow.springmvc.mapping;

import com.livenow.springmvc.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/uri-pattern")
public class UriPatternController {

    public ResponseEntity<User> pathVariable(Long id) {
        User user = new User(id, "이름", "email");
        return ResponseEntity.ok().body(user);
    }

    public ResponseEntity<String> pattern() {
        return ResponseEntity.ok().body("pattern");
    }

    public ResponseEntity<String> patternStars() {
        return ResponseEntity.ok().body("pattern-multi");
    }
}