package br.com.football.player.skill.validator;


import br.com.football.player.skill.exception.ErrorCode;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotação utilizada para realizar a validação de campos que serão mapeados para uma enum.
 *
 * @author alvesfc
 * @version 1.0
 */
@Documented
@Constraint(validatedBy = {CollectionValueValidator.class})
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface CollectionValue {

  String message() default ErrorCode.FIELD_IS_INVALID;

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  Class<? extends Enum<?>> enumClass();

  boolean ignoreCase() default true;

  boolean nullable() default false;
}
