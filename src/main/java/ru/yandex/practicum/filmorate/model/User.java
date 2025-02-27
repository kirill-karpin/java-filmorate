package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;
import lombok.Data;

/**
 * User.
 */
@Data
public class User {

  private long id;
  @NotBlank
  @Email
  protected String email;

  @NotBlank(message = "Login can't be blank")
  @Pattern(regexp = "^\\S+$", message = "Login no contain space")
  private String login;

  protected String name;
  @Past
  protected LocalDate birthday;

  public @NotBlank(message = "Name can't be blank") String getName() {
    return (name != null ? name : login);
  }
}
