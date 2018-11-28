package br.com.football.player.skill.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;


public class CollectionValueValidator implements ConstraintValidator<CollectionValue, Collection<String>> {

  private CollectionValue annotation;

  @Override
  public void initialize(final CollectionValue annotation) {
    this.annotation = annotation;
  }

  @Override
  public boolean isValid(final Collection<String> values, final ConstraintValidatorContext context) {
    Object[] enumValues = this.annotation.enumClass().getEnumConstants();

    if (enumValues != null) {
      if (this.annotation.nullable()) {
        return true;
      } else if (values == null) {
        return true;
      } else {
        return values.stream()
                .map(v -> exists(enumValues, v))
                .filter(result -> !result)
                .collect(Collectors.toList())
                .isEmpty();
      }
    }
    return false;
  }

  private boolean exists(final Object[] enumValues, final String value) {
    return Arrays.stream(enumValues)
            .filter(enumValue -> enumValue.toString().equals(value)
                    || (this.annotation.ignoreCase() &&
                    enumValue.toString().equalsIgnoreCase(value)))
            .map(Objects::nonNull)
            .findFirst()
            .orElse(Boolean.FALSE);
  }
}
