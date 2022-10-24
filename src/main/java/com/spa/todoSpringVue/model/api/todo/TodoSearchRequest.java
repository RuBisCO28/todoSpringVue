package com.spa.todoSpringVue.model.api.todo;

import com.spa.todoSpringVue.validation.constraint.DateOrder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Optional;

@Data
@NoArgsConstructor
@DateOrder
public class TodoSearchRequest {
  @Size(max=50)
  private String title;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate from;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate to;

  @Pattern(regexp = "([012])")
  private String status;

  public String getTitle() {
    return Optional.ofNullable(title).orElse("");
  }
}
