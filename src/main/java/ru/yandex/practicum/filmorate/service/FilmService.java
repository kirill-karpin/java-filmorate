package ru.yandex.practicum.filmorate.service;

import jakarta.validation.Valid;
import java.util.Collection;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

@Service
public class FilmService {

  final
  FilmRepository filmRepository;
  private final UserRepository userRepository;

  public FilmService(FilmRepository filmRepository, UserRepository userRepository) {
    this.filmRepository = filmRepository;
    this.userRepository = userRepository;
  }


  public int create(@Valid Film film) {
    return filmRepository.create(film);
  }


  public Film read(int id) {
    return filmRepository.read(id);
  }

  public Film update(@Valid Film film) {
    return filmRepository.update(film);
  }

  public Collection<Film> getAll() {
    return filmRepository.getAll();
  }

  public void addLike(int id, int userId) {
    Film film = filmRepository.read(id);
    if (film == null) {
      throw new NotFoundException("Film not found");
    }

    User user = userRepository.read(userId);
    if (user == null) {
      throw new NotFoundException("User not found");
    }

    film.addLike(userId);

  }

  public void removeLike(int id, int userId) {

    Film film = filmRepository.read(id);
    if (film == null) {
      throw new NotFoundException("Film not found");
    }

    User user = userRepository.read(userId);
    if (user == null) {
      throw new NotFoundException("User not found");
    }

    film.removeLike(userId);
  }
}
