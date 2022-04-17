package com.openfeign.data.dto;

public class EmptyResponse {
  public static final EmptyResponse INSTANCE = new EmptyResponse();

  private EmptyResponse() {
  }
}
