package com.spa.todoSpringVue.dao;

import com.spa.todoSpringVue.model.entity.TodoEntity;
import lombok.NonNull;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("TodoDAO")
@Mapper
public interface TodoDAO {
  List<TodoEntity> findAll();

  TodoEntity findById(Long id);

  void save(TodoEntity todoEntity);

  void update(TodoEntity todoEntity);

  void delete(Long id);

  List<TodoEntity> search(@NonNull String title, @Nullable String from, @Nullable String to, @Nullable String status);
}
