package com.spa.todoSpringVue.validation.constraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = DateOrderImpl.class)
@Target({ ElementType.TYPE })
@Retention(RUNTIME)
public @interface DateOrder {
  String message() default "終了日は開始日より過去にはできません";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}
