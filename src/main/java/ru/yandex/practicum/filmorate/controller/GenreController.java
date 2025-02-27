package ru.yandex.practicum.filmorate.controller;

import java.util.Collection;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.film.Genre;
import ru.yandex.practicum.filmorate.service.FilmService;

@RestController
@RequestMapping("/genres")
@Slf4j
public class GenreController {

  private final FilmService filmService;

  public GenreController(FilmService filmService) {
    this.filmService = filmService;
  }

  @GetMapping()
  public Collection<Genre> getAllGenres() {
    return filmService.getAllGenres();
  }

  @GetMapping("/{id}")
  public Optional<Genre> getGenresById(@PathVariable long id) {
    Optional<Genre> genre = filmService.getGenreById(id);
    if (genre.isEmpty()) {
      throw new NotFoundException("Genre not found");
    }
    return genre;
  }
}
