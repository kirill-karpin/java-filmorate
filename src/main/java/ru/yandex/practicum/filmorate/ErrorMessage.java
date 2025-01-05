package ru.yandex.practicum.filmorate;

import java.util.Map;

public class ErrorMessage {

  public final Map<String, String> errors;

  public ErrorMessage(Map<String, String> errors) {
    this.errors = errors;
  }
}
