package ru.yandex.practicum.filmorate.model.film;

import lombok.Data;

@Data
public class Like {

  private Long id;
  private Long filmId;
  private Long userId;
}
