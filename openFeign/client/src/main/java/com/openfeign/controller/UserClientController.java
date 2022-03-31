package com.openfeign.controller;


import com.openfeign.data.dto.UserDto;
import com.openfeign.service.UserClientService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client/v1/users")
@RequiredArgsConstructor
public class UserClientController {

  private final UserClientService userClientService;

  @PostMapping
  public UserDto create(String name) {
    return userClientService.create(name);
  }

  @GetMapping
  public List<UserDto> getAll() {
    return userClientService.getAll();
  }
}
