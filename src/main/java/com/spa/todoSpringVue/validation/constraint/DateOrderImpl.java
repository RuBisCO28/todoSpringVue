package com.spa.todoSpringVue.validation.constraint;

import com.spa.todoSpringVue.model.api.todo.TodoSearchRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateOrderImpl implements ConstraintValidator<DateOrder, TodoSearchRequest> {
  @Override
  public boolean isValid(TodoSearchRequest request, ConstraintValidatorContext context) {
    if (request.getFrom() == null) return true;
    if (request.getTo() == null) return true;
    return request.getTo().isAfter(request.getFrom());
  }
}
