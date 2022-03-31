package com.openfeign.controller;

import com.openfeign.data.dto.UserDto;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-client", url = "localhost:7777")
public interface UserClient {

  @PostMapping("/server/v1/users")
  UserDto createUser(@RequestParam String name);

  @GetMapping("/server/v1/users")
  List<UserDto> getAllUsers();
}
