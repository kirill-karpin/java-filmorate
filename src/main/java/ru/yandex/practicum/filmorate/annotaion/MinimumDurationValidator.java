package ru.yandex.practicum.filmorate.annotaion;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.Duration;

public class MinimumDurationValidator implements ConstraintValidator<MinimumDuration, Duration> {

  @Override
  public boolean isValid(Duration duration, ConstraintValidatorContext constraintValidatorContext) {
    return !duration.isNegative();
  }

  @Override
  public void initialize(MinimumDuration constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }
}
