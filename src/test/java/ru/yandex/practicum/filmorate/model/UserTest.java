package ru.yandex.practicum.filmorate.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Map;
import org.junit.jupiter.api.Test;

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
