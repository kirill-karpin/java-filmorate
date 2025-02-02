package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;

/**
 * User.
 */
@Data
public class User {

  private int id;
  @NotBlank
  @Email
  private String email;

  @NotBlank(message = "Login can't be blank")
  @Pattern(regexp = "^\\S+$", message = "Login no contain space")
  private String login;

  private String name;
  @Past
  private LocalDate birthday;

  public @NotBlank(message = "Name can't be blank") String getName() {
    return (name != null ? name : login);
  }

  private Set<Integer> friends = new HashSet<>();

  public void addFriend(Integer id) {
    friends.add(id);
  }

  public void removeFriend(Integer id) {
    friends.remove(id);
  }

}
