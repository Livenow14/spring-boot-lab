package com.openfeign.service;

import com.openfeign.data.dto.UserDto;
import com.openfeign.data.entity.User;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private static final Map<Long, User> DB = new ConcurrentHashMap<>();
  private static long sequence = 1;

  public UserDto create(String name) {
    User user = User.builder()
        .id(sequence)
        .name(name)
        .build();
    DB.put(sequence++, user);
    return new UserDto(user.getId(), user.getName());
  }

  public List<UserDto> getAll() {
    return DB.values().stream()
        .map(user -> new UserDto(user.getId(), user.getName()))
        .collect(Collectors.toList());
  }

  public void update(Long userId, String name) {
    User user = DB.get(userId);
    if (Objects.isNull(user)) {
      return;
    }
    user.changeName(name);
  }

  public void delete(Long userId) {
    DB.remove(userId);
  }
}
