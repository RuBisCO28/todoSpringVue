package com.spa.todoSpringVue.service;

public class TodoNotFoundException extends RuntimeException {
  private static final Long serialVersionID = 1L;

  public TodoNotFoundException(String message) {
    super(message);
  }
}
