package com.livenow.springrestdocs.controller.dto;

import lombok.Getter;

@Getter
public class UserResponse {

    private String name;

    public UserResponse(String name) {
        this.name = name;
    }
}
