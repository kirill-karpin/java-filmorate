package ru.yandex.practicum.filmorate.model;


import jakarta.validation.constraints.NotBlank;
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
  private String name;

  @NotBlank
  @Size(max = 200, message = "Length of description should be less than 200")
  private String description;

  @MinimumDate
  private LocalDate releaseDate;

  @MinimumDuration()
  private Duration duration;
}
