package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.model.Film;

@RestController
@RequestMapping("/films")
public class FilmController {

  @PostMapping()
  public Film createFilm(@Valid @RequestBody Film film) {
    return film;
  }

  @PutMapping()
  public Film updateFilm(@Valid @RequestBody Film film) {
    return film;
  }

  @GetMapping()
  public Collection<Film> listFilm() {
    return new ArrayList<>();
  }
}
