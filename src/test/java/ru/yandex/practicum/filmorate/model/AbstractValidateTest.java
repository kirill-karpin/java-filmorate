package ru.yandex.practicum.filmorate.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class AbstractValidateTest {
  public <T> Map<String, String> validate(T entity) {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();
    Set<ConstraintViolation<T>> violations = validator.validate(entity);
    Map<String, String> result = new HashMap<>();
    for(ConstraintViolation<T> violation : violations) {
      result.put(violation.getPropertyPath().toString(), violation.getMessage());
    }
    return result;
  }

}
