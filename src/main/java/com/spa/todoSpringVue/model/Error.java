package com.spa.todoSpringVue.model;

import lombok.NonNull;
import lombok.Value;
import org.springframework.validation.FieldError;

@Value
public class Error {
  private final String fieldName;
  private final String message;

  public Error(@NonNull final FieldError fieldError) {
    fieldName = fieldError.getField();
    message = fieldError.getDefaultMessage();
  }
}
