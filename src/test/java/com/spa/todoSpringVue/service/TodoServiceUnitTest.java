package com.spa.todoSpringVue.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.spa.todoSpringVue.dao.TodoDAO;
import com.spa.todoSpringVue.model.api.todo.TodoRequest;
import com.spa.todoSpringVue.model.entity.TodoEntity;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("TodoServiceの単体テスト")
public class TodoServiceUnitTest {

  @Mock
  private TodoDAO dao;

  @InjectMocks
  private TodoService todoService;

  @Test
  @DisplayName("テーブルtodosの全件取得で0件の場合")
  void testFindAllReturnEmptyList() {
    List<TodoEntity> list = new ArrayList<>();
    when(dao.findAll()).thenReturn(list);

    List<TodoEntity> actualList = todoService.getAll();
    verify(dao, times(1)).findAll();
    assertEquals(0, actualList.size());
  }

  @Test
  @DisplayName("テーブルTaskの全件取得で2件の場合")
  void testFindAllReturnList() {
    List<TodoEntity> list = new ArrayList<>();
    TodoEntity todo1 = new TodoEntity();
    TodoEntity todo2 = new TodoEntity();
    list.add(todo1);
    list.add(todo2);

    when(dao.findAll()).thenReturn(list);
    List<TodoEntity> actualList = todoService.getAll();
    verify(dao, times(1)).findAll();
    assertEquals(2,actualList.size());
  }

  @Test
  @DisplayName("Todoが取得できない場合")
  void testGetTodoThrowException() {
    Long notExistId = 0L;
    when(dao.findById(notExistId)).thenThrow(new TodoNotFoundException("指定されたTodoが存在しません"));
    try {
      TodoEntity todo0 = todoService.getTodo(notExistId);
    } catch(TodoNotFoundException e) {
      assertEquals(e.getMessage(), "指定されたTodoが存在しません");
    }
  }

  @Test
  @DisplayName("todoを1件取得した場合")
  void testGetTodoReturnOne() {
    Long id = 1L;
    TodoEntity todo = new TodoEntity();

    when(dao.findById(id)).thenReturn(todo);
    TodoEntity todoActual = todoService.getTodo(id);
    verify(dao, times(1)).findById(id);
    assertEquals(todoActual, todo);
  }

  @Test
  @DisplayName("更新対象が存在しない場合、例外を発生することを確認")
  void UpdatethrowNotFoundException() {
    Long notExistId = 0L;
    TodoRequest request = new TodoRequest();
    try {
      todoService.update(request, notExistId);
    } catch (TodoNotFoundException e) {
      assertEquals(e.getMessage(), "更新するTodoが存在しません");
    }
  }

  @Test
  @DisplayName("削除対象が存在しない場合、例外を発生することを確認")
  void DeletethrowNotFoundException() {
    Long notExistId = 0L;
    try {
      todoService.delete(notExistId);
    } catch (TodoNotFoundException e) {
      assertEquals(e.getMessage(), "削除するTodoが存在しません");
    }
  }
}
