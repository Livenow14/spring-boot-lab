package com.openfeign.service;

import com.openfeign.controller.UserClient;
import com.openfeign.data.dto.UserDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.loadbalancer.EmptyResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserClientService {

  private final UserClient userClient;

  public UserDto create(String name) {
    return userClient.createUser(name);
  }

  public List<UserDto> getAll() {
    return userClient.getAllUsers();
  }

  public void error() {
    EmptyResponse emptyResponse = userClient.error();
    log.info("찍히나여기!!");
  }
}
