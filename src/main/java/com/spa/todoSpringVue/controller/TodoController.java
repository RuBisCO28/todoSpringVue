package com.spa.todoSpringVue.controller;

import com.spa.todoSpringVue.model.api.ApiResponse;
import com.spa.todoSpringVue.model.api.ErrorMessages;
import com.spa.todoSpringVue.model.api.todo.TodoRequest;
import com.spa.todoSpringVue.model.api.todo.TodoResponse;
import com.spa.todoSpringVue.model.api.todo.TodoSearchRequest;
import com.spa.todoSpringVue.model.entity.TodoEntity;
import com.spa.todoSpringVue.service.TodoService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RequestMapping("api/todos")
@RestController
@RequiredArgsConstructor
public class TodoController {

  private final TodoService todoService;

  @GetMapping
  public List<TodoResponse> get() {
    final List<TodoEntity> todos = todoService.getAll();
    return todos.stream().map(TodoResponse::from).collect(Collectors.toList());
  }

  @GetMapping("{id}")
  public TodoResponse getById(@PathVariable("id") @NonNull final Long id) {
    final TodoEntity todo = todoService.getTodo(id);
    return TodoResponse.from(todo);
  }

  @PostMapping
  public ApiResponse<?> insert(@RequestBody @Validated TodoRequest request, @NonNull BindingResult result) {
    if(result.hasErrors()) {
      return ApiResponse.failed(HttpStatus.BAD_REQUEST.value(), "Invalid message insert request", new ErrorMessages(result.getFieldErrors()));
    }
    todoService.create(request);
    return ApiResponse.success("insert success");
  }

  @PutMapping("{id}")
  public ApiResponse<?> update(@PathVariable("id") @NonNull final Long id, @RequestBody @Validated TodoRequest request, @NonNull BindingResult result) {
    if (result.hasErrors()) {
      return ApiResponse.failed(HttpStatus.BAD_REQUEST.value(), "Invalid message update request", new ErrorMessages(result.getFieldErrors()));
    }
    todoService.update(request, id);
    return ApiResponse.success("update success");
  }

  @DeleteMapping("{id}")
  public ApiResponse<?> delete(@PathVariable("id") @NonNull final Long id) {
    todoService.delete(id);
    return ApiResponse.success("delete success");
  }

  @GetMapping("/search")
  public List<TodoResponse> search(@Validated TodoSearchRequest request) {
    final List<TodoEntity> todos = todoService.search(request.getTitle(), request.getFrom(), request.getTo(), request.getStatus());
    return todos.stream().map(TodoResponse::from).collect(Collectors.toList());
  }
}
