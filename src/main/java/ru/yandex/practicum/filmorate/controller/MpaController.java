package ru.yandex.practicum.filmorate.controller;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.model.film.Mpa;
import ru.yandex.practicum.filmorate.service.FilmService;

@RestController
@RequestMapping("/mpa")
@Slf4j
public class MpaController {

  private final FilmService filmService;

  public MpaController(FilmService filmService) {
    this.filmService = filmService;
  }

  @GetMapping()
  public List<Mpa> getAllGenres() {
    return filmService.getAllMpa();
  }

  @GetMapping("/{id}")
  public Mpa getGenresById(@PathVariable long id) {
    return filmService.getMpaById(id).get();
  }
}
