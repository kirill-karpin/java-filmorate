package ru.yandex.practicum.filmorate.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exception.ValidationException;

class UserTest extends AbstractValidateTest {

  @Test
  void invalidValuesTest() {
    User user = new User();
    user.setLogin("lo gin");
    user.setEmail("email");
    user.setBirthday(LocalDate.parse("2446-08-20"));

    Map<String, String> valid = validate(user);

    assertTrue(valid.containsKey("login"));
    assertTrue(valid.containsKey("email"));
    assertTrue(valid.containsKey("birthday"));
    assertTrue(valid.containsKey("name"));
  }
}
