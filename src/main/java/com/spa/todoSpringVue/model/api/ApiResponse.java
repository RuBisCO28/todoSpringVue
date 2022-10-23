package com.spa.todoSpringVue.model.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.spa.todoSpringVue.model.Status;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiResponse<T> {
  protected static final Status STATUS_OK = new Status(HttpStatus.OK.value(), "");

  private Status status;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private T result;

  public static <T> ApiResponse<T> success(T body) { return new ApiResponse<>(STATUS_OK, body); }

  public static ApiResponse failed(int code, String message, ErrorMessages errorMessages) {
    return new ApiResponse(new Status(code, message), errorMessages);
  }
}
