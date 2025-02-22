package ru.yandex.practicum.filmorate.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.Duration;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
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

  private Set<Integer> likes = new HashSet<>();

  @JsonProperty("duration")
  public long getDurationSeconds() {
    return duration.toSeconds();
  }

  public void addLike(int userId) {
    likes.add(userId);
  }

  public void removeLike(int userId) {
    likes.remove(userId);
  }

  public int likesCount() {
    return likes.size();
  }
}
