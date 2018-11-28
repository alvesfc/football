package br.com.football.player.skill.validator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author alvesfc
 * @version 1.0
 */
@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = AtLeastOneValidator.class)
public @interface AtLeastOne {
  String message() default "{javax.validation.constraints.Min.message}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
