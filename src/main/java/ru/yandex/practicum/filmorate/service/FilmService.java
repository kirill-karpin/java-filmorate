package ru.yandex.practicum.filmorate.service;

import jakarta.validation.Valid;
import java.util.Collection;
import java.util.Optional;
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


  public long create(@Valid Film film) {
    return filmRepository.create(film);
  }


  public Optional<Film> read(long id) {
    return filmRepository.read(id);
  }

  public Optional<Film> update(@Valid Film filmUpdate) {
    Optional<Film> film = filmRepository.read(filmUpdate.getId());
    if (film.isEmpty()) {
      throw new NotFoundException("Film not found");
    }
    return filmRepository.update(filmUpdate);
  }

  public Collection<Film> getAll() {
    return filmRepository.getAll();
  }

  public void addLike(int id, int userId) {
    Optional<Film> film = filmRepository.read(id);
    if (film.isEmpty()) {
      throw new NotFoundException("Film not found");
    }

    Optional<User> user = userRepository.read(userId);
    if (user.isEmpty()) {
      throw new NotFoundException("User not found");
    }

    film.get().addLike(userId);

  }

  public void removeLike(int id, int userId) {

    Optional<Film> film = filmRepository.read(id);
    if (film.isEmpty()) {
      throw new NotFoundException("Film not found");
    }

    Optional<User> user = userRepository.read(userId);
    if (user.isEmpty()) {
      throw new NotFoundException("User not found");
    }

    film.get().removeLike(userId);
  }

  public Collection<Film> getPopularFilms(int count) {
    return filmRepository.getPopularFilms(count);
  }
}
