package com.spa.todoSpringVue.service;

import com.spa.todoSpringVue.dao.TodoDAO;
import com.spa.todoSpringVue.model.api.todo.TodoRequest;
import com.spa.todoSpringVue.model.entity.TodoEntity;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
  private final TodoDAO todoDAO;

  public List<TodoEntity> getAll() { return todoDAO.findAll(); }

  public TodoEntity getTodo(Long id) {
    final var todoEntity = todoDAO.findById(id);
    if(todoEntity == null) {
      throw new TodoNotFoundException("指定されたTodoが存在しません");
    }
    return todoEntity;
  }

  public void create(TodoRequest request) {
    final TodoEntity todoEntity = TodoEntity.from(request);
    todoDAO.save(todoEntity);
  }

  public void update(TodoRequest request, Long id) {
    try {
      final TodoEntity todo = todoDAO.findById(id);
      todo.modify(request);
      todoDAO.update(todo);
    } catch (NullPointerException e) {
      throw new TodoNotFoundException("更新するTodoが存在しません");
    }
  }

  public void delete(Long id) {
    try {
      final TodoEntity todo = todoDAO.findById(id);
      todoDAO.delete(todo.getId());
    } catch (NullPointerException e) {
      throw new TodoNotFoundException("削除するTodoが存在しません");
    }
  }

  public List<TodoEntity> search(@NonNull final String title, @Nullable final LocalDate from, @Nullable final LocalDate to, @Nullable final String status) {
    String fromFmt = null;
    String toFmt = null;
    if (from != null) {
      fromFmt = from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00"));
    }
    if (to != null) {
      toFmt = to.plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00"));
    }
    return todoDAO.search(title, fromFmt, toFmt, status);
  }
}
