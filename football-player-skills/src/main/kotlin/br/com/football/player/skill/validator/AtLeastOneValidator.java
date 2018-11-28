package br.com.football.player.skill.validator;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collection;

/**
 * @author alvesfc
 * @version 1.0
 */
public class AtLeastOneValidator implements ConstraintValidator<AtLeastOne, Collection> {

  @Override
  public void initialize(final AtLeastOne constraintAnnotation) {

  }

  @Override
  public boolean isValid(final Collection value, final ConstraintValidatorContext context) {
    if (value == null) {
      return false;
    }
    return !value.isEmpty();
  }
}
