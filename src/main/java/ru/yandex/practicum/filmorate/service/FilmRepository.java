package ru.yandex.practicum.filmorate.service;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;

@Component
public class FilmRepository extends CrudRepository<Film> {

  @Override
  public int getEntityId(Film film) {
    return film.getId();
  }

  @Override
  public int setEntityId(int id, Film film) {
    film.setId(id);
    return id;
  }

  public Collection<Film> getPopularFilms(int count) {
    return getAll().stream().sorted((f1, f2) -> f2.likesCount() - f1.likesCount())
        .limit(count)
        .collect(Collectors.toList());
  }
}
