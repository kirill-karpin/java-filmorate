package ru.yandex.practicum.filmorate.annotaion;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class MinimumDateValidator implements ConstraintValidator<MinimumDate, LocalDate> {

  private LocalDate minimumDate;

  @Override
  public void initialize(MinimumDate constraintAnnotation) {
    minimumDate = LocalDate.parse(constraintAnnotation.value());
  }

  @Override
  public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
    return value != null && minimumDate.isBefore(value);
  }
}
