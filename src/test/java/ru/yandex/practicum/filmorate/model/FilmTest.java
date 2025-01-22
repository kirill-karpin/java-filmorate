package ru.yandex.practicum.filmorate.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Map;
import org.junit.jupiter.api.Test;

class FilmTest extends AbstractValidateTest {

  @Test
  void invalidValuesTest() {
    Film film = new Film();
    film.setName("");
    film.setDuration(Duration.ZERO.minusMinutes(10));
    film.setDescription("");
    film.setReleaseDate(LocalDate.parse("1111-01-01"));

    Map<String, String> valid = validate(film);

    assertTrue(valid.containsKey("name"));
    assertTrue(valid.containsKey("duration"));
    assertTrue(valid.containsKey("description"));
    assertTrue(valid.containsKey("releaseDate"));
  }

  @Test
  void validValuesTest() {
    Film film = new Film();
    film.setName("foo");
    film.setDuration(Duration.ZERO.plusMinutes(10));
    film.setDescription("adfasdfadsf");
    film.setReleaseDate(LocalDate.now());

    Map<String, String> valid = validate(film);

    assertEquals(valid.size(), 0);
  }
}

