package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.Date;
import lombok.Data;
import ru.yandex.practicum.filmorate.exception.ValidationException;

/**
 * User.
 */
@Data
public class User {

  private int id;
  @NotBlank
  @NotNull(message = "Name can't be null")
  @Email
  private String email;

  @NotBlank(message = "Login can't be blank")
  @NotNull(message = "Login can't be null")
  @Pattern(regexp = "^\\S+$", message = "Login no contain space")
  private String login;

  @NotBlank(message = "Name can't be blank")
  @NotNull(message = "Name can't be null")
  private String name;
  @Past
  private LocalDate birthday;

  public @NotBlank(message = "Name can't be blank") @NotNull(message = "Name can't be null") String getName() {
    return (name != null ? name : login);
  }
}
