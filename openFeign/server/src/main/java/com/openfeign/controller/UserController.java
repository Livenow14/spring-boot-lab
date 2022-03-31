package com.openfeign.controller;

import com.openfeign.data.dto.UserDto;
import com.openfeign.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/server/v1/users")
public class UserController {

  private final UserService service;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public UserDto create(@RequestParam String name) {
    return service.create(name);
  }

  @GetMapping
  public List<UserDto> getAll() {
    return service.getAll();
  }

  @PutMapping("/{userId}")
  public void update(@PathVariable Long userId, @RequestParam String name) {
    service.update(userId, name);
  }

  @DeleteMapping("/{userId}")
  public void update(@PathVariable Long userId) {
    service.delete(userId);
  }
}
