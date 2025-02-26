package ru.yandex.practicum.filmorate.service;

import jakarta.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.dal.FilmRepository;
import ru.yandex.practicum.filmorate.dal.GenreRepository;
import ru.yandex.practicum.filmorate.dal.LikeRepository;
import ru.yandex.practicum.filmorate.dal.MpaRepository;
import ru.yandex.practicum.filmorate.dal.UserRepository;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.model.film.Film;
import ru.yandex.practicum.filmorate.model.film.Genre;
import ru.yandex.practicum.filmorate.model.film.Mpa;

@Service
public class FilmService {


  private final FilmRepository filmRepository;
  private final UserRepository userRepository;
  private final GenreRepository genreRepository;
  private final MpaRepository mpaRepository;
  private final LikeRepository likeRepository;


  public FilmService(FilmRepository filmRepository, UserRepository userRepository,
      MpaRepository mpaRepository, LikeRepository likeRepository, GenreRepository genreRepository) {
    this.filmRepository = filmRepository;
    this.userRepository = userRepository;
    this.mpaRepository = mpaRepository;
    this.likeRepository = likeRepository;
    this.genreRepository = genreRepository;
  }


  public long create(@Valid Film film) {
    long id = filmRepository.create(film);

    Mpa mpa = film.getMpa();
    if (mpaRepository.findById(mpa.getId()).isPresent()) {
      mpaRepository.addFilmMpa(id, mpa.getId());
    } else {
      throw new NotFoundException("Mpa not found");
    }

    List<Genre> genres = film.getGenres();
    genres.forEach(genre -> {
      if (genreRepository.findById(genre.getId()).isPresent()) {
        genreRepository.addFilmGenre(id, genre.getId());
      } else {
        throw new NotFoundException("Genre not found");
      }
    });

    return id;
  }


  public Optional<Film> read(long id) {
    Optional<Film> film = filmRepository.read(id);
    if (film.isEmpty()) {
      throw new NotFoundException("Film not found");
    }
    if (film.get().getMpaId() != null) {
      film.get().setMpa(mpaRepository.findById(film.get().getMpaId()).get());
    }

    film.get().setGenres(genreRepository.findGenresByFilmId(film.get().getId()));

    return film;
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

    likeRepository.addLike(id, userId);

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

    likeRepository.removeLike(id, userId);
  }

  public Collection<Film> getPopularFilms(int count) {
    return filmRepository.getPopularFilms(count);
  }

  public Collection<Genre> getAllGenres() {
    return genreRepository.getAll();
  }

  public Optional<Genre> getGenreById(long id) {
    return genreRepository.findById(id);
  }
}
