package com.spa.todoSpringVue.model.api.todo;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
public class TodoRequest {
  @NotNull
  @Size(min=1, max=50)
  private String title;

  @NotNull
  @Range(min=0, max=2)
  private int status;

  @Nullable
  private LocalDateTime deadline;
}
