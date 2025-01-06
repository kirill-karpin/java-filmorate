package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import java.util.Collection;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmRepository;

@RestController
@RequestMapping("/films")
public class FilmController {
  private final FilmRepository filmService;

  public FilmController(FilmRepository filmService) {
    this.filmService = filmService;
  }

  @PostMapping()
  public Film createFilm(@Valid @RequestBody Film film) {
    int id = filmService.create(film);
    return filmService.read(id);
  }

  @PutMapping()
  public Film updateFilm(@Valid @RequestBody Film film) {
    return filmService.update(film);
  }

  @GetMapping()
  public Collection<Film> listFilm() {
    return filmService.getAll();
  }
}
