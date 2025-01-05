package ru.yandex.practicum.filmorate.model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.Duration;
import java.time.LocalDate;
import lombok.Data;
import ru.yandex.practicum.filmorate.annotaion.MinimumDate;
import ru.yandex.practicum.filmorate.annotaion.MinimumDuration;


/**
 * Film
 */
@Data
public class Film {

  private int id;

  @NotBlank
  @NotNull(message = "Length of name should be less than 50")
  private String name;

  @NotBlank
  @NotNull
  @Size(max = 200, message = "Length of description should be less than 200")
  private String description;

  @MinimumDate
  private LocalDate releaseDate;

  @MinimumDuration(value = 0)
  private Duration duration;
}
