package com.spa.todoSpringVue.model.entity;

import com.spa.todoSpringVue.model.api.todo.TodoRequest;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TodoEntity {
  private Long id;
  private String title;
  private int status;
  private LocalDateTime deadline;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public static TodoEntity from(TodoRequest request) {
    final TodoEntity entity = new TodoEntity();
    entity.setTitle(request.getTitle());
    entity.setStatus(request.getStatus());
    entity.setDeadline(request.getDeadline());
    entity.setCreatedAt(LocalDateTime.now());
    entity.setUpdatedAt(LocalDateTime.now());
    return entity;
  }

  public void modify(TodoRequest request) {
    setTitle(request.getTitle());
    setStatus(request.getStatus());
    setDeadline(request.getDeadline());
    setUpdatedAt(LocalDateTime.now());
  }
}
