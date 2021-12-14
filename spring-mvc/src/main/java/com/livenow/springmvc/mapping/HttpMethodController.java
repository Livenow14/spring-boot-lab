package com.livenow.springmvc.mapping;

import com.livenow.springmvc.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/http-method")
public class HttpMethodController {

    @PostMapping("/users")
    public ResponseEntity createUser(@RequestBody User user) {
        Long id = 1L;
        return ResponseEntity.created(URI.create("/users/" + id)).build();
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> showUser() {
        List<User> users = Arrays.asList(
                new User("이름", "email"),
                new User("이름", "email")
        );
        return ResponseEntity.ok().body(users);
    }

    @PatchMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User originUser = new User("이름", "email@email.com");
        originUser.setName(user.getName());
        return ResponseEntity.ok().body(originUser);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> changeUser(@PathVariable Long id, @RequestBody User user) {
        User originUser = new User("이름");
        originUser.setName(user.getName());
        return ResponseEntity.ok().body(originUser);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }

}