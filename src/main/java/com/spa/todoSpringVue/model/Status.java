package com.spa.todoSpringVue.model;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class Status {
  private int code;
  private String message;
}
