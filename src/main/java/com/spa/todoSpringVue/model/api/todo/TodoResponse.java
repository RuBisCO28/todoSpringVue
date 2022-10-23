package com.spa.todoSpringVue.model.api.todo;

import com.spa.todoSpringVue.model.entity.TodoEntity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TodoResponse {
  private Long id;
  private String title;
  private int status;
  private LocalDateTime deadline;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public static TodoResponse from(TodoEntity entity) {
    final TodoResponse response = new TodoResponse();
    response.setId(entity.getId());
    response.setTitle(entity.getTitle());
    response.setStatus(entity.getStatus());
    response.setDeadline(entity.getDeadline());
    response.setCreatedAt(entity.getCreatedAt());
    response.setUpdatedAt(entity.getUpdatedAt());
    return response;
  }
}
