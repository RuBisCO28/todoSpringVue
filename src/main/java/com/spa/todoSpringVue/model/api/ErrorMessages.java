package com.spa.todoSpringVue.model.api;

import lombok.Getter;
import lombok.NonNull;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.stream.Collectors;

public class ErrorMessages {
  @Getter
  private final List<String> messages;

  public ErrorMessages(@NonNull List<FieldError> errors) {
    messages = errors.stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
  }

  public ErrorMessages(@NonNull BindingResult result) {
    messages = result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());
  }
}
