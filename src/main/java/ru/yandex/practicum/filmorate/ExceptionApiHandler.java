package ru.yandex.practicum.filmorate;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.View;

@RestControllerAdvice
public class ExceptionApiHandler {

  private final View error;

  public ExceptionApiHandler(View error) {
    this.error = error;
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorMessage> validationException(
      MethodArgumentNotValidException exception) {
    Map<String, String> result = new HashMap<>();
    exception.getBindingResult().getFieldErrors()
        .forEach(error -> result.put(error.getField(), error.getDefaultMessage()));

    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(new ErrorMessage(result));
  }
}
