package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import java.util.Collection;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmRepository;

@RestController
@RequestMapping("/films")
@Slf4j
public class FilmController {

  private final FilmRepository filmRepository;

  public FilmController(FilmRepository filmRepository) {
    this.filmRepository = filmRepository;
  }

  @PostMapping()
  public Film createFilm(@Valid @RequestBody Film film) {
    log.info("Create film: " + film);
    int id = filmRepository.create(film);
    return filmRepository.read(id);
  }

  @PutMapping()
  public Film updateFilm(@Valid @RequestBody Film film) {
    log.info("Update film: " + film);
    return filmRepository.update(film);
  }

  @GetMapping()
  public Collection<Film> listFilm() {
    return filmRepository.getAll();
  }

  @PutMapping("/films/{id}/like/{userId}")
  public void likeFilm(@PathVariable int id, @PathVariable int userId) {
    log.info("Like film: " + id + " " + userId);
  }

  @DeleteMapping("/films/{id}/like/{userId}")
  public void dislikeFilm(@PathVariable int id, @PathVariable int userId) {
    log.info("Dislike film: " + id + " " + userId);
  }
  @GetMapping("/films/popular?count={count}")
  public Collection<Film> getPopularFilms(@PathVariable int count) {
    log.info("Get popular films: " + count);
    return filmRepository.getPopularFilms(count);
  }


}
