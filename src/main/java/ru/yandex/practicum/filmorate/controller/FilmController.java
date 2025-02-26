package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import java.util.Collection;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.model.film.Film;
import ru.yandex.practicum.filmorate.service.FilmService;

@RestController
@RequestMapping("/films")
@Slf4j
public class FilmController {

  private final FilmService filmService;


  public FilmController(FilmService filmService) {
    this.filmService = filmService;
  }

  @PostMapping()
  public Optional<Film> createFilm(@Valid @RequestBody Film film) {
    log.info("Create film: " + film);
    long id = filmService.create(film);
    var result = filmService.read(id);
    return result;
  }

  @PutMapping()
  public Optional<Film> updateFilm(@Valid @RequestBody Film film) {
    log.info("Update film: " + film);
    return filmService.update(film);
  }

  @GetMapping()
  public Collection<Film> listFilm() {
    return filmService.getAll();
  }

  @PutMapping("/{id}/like/{userId}")
  public void addLike(@PathVariable int id, @PathVariable int userId) {
    log.info("Like film: " + id + " " + userId);
    filmService.addLike(id, userId);
  }

  @DeleteMapping("/{id}/like/{userId}")
  public void removeLike(@PathVariable int id, @PathVariable int userId) {
    log.info("Dislike film: " + id + " " + userId);
    filmService.removeLike(id, userId);
  }

  @GetMapping("/popular")
  public Collection<Film> getPopularFilms(@RequestParam int count) {
    log.info("Get popular films: " + count);
    return filmService.getPopularFilms(count);
  }


}
