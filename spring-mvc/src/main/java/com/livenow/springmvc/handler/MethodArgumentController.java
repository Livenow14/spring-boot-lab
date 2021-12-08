package com.livenow.springmvc.handler;

import com.livenow.springmvc.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/method-argument")
public class MethodArgumentController {

    /**
     * Url 요청 파라미터와, 메소드의 파라미터 명이 같으면 @RequestParam 생략가능
     */
    @GetMapping("/users")
    public ResponseEntity<List<User>> requestParam(@RequestParam(value = "name") String name) {
        List<User> users = Arrays.asList(
                new User(name, "email"),
                new User(name, "email")
        );
        return ResponseEntity.ok().body(users);
    }

    /**
     * ModelAttribute 애노테이션 생략가능
     */
    @GetMapping("/users/model-attribute")
    public ResponseEntity<List<User>> modelAttribute(@ModelAttribute User user) {
        List<User> users = Arrays.asList(
                user,
                user
        );
        return ResponseEntity.ok().body(users);
    }

    @PostMapping("/users/body")
    public ResponseEntity requestBody(@RequestBody User user) {
        User newUser = new User(1L, user.getName(), user.getEmail());
        return ResponseEntity.created(URI.create("/users/" + newUser.getId())).body(newUser);
    }

}