package com.openfeign.service;

import com.openfeign.controller.UserClient;
import com.openfeign.data.dto.UserDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserClientService {

  private final UserClient userClient;

  public UserDto create(String name) {
    return userClient.createUser(name);
  }

  public List<UserDto> getAll() {
    return userClient.getAllUsers();
  }
}
