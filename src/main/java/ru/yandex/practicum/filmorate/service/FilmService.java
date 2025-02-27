package ru.yandex.practicum.filmorate.service;

import jakarta.validation.Valid;
import java.util.Collection;
import java.util.LinkedHashSet;
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

    Mpa mpa = film.getMpa();
    if (mpaRepository.findById(mpa.getId()).isEmpty()) {
      throw new NotFoundException("Mpa not found");
    }
    List<Long> genresId = film.getGenres().stream().map(Genre::getId).toList();
    List<Genre> genresUpdate = genreRepository.listGenresByIds(genresId);

    if (genresUpdate.size() != film.getGenres().size()) {
      throw new NotFoundException("Genre not found");
    }
    long id = filmRepository.create(film);

    genreRepository.batchUpdateFilmGenres(id, film.getGenres().stream().toList());

    return id;
  }


  public Optional<Film> read(long id) {
    Optional<Film> film = filmRepository.read(id);
    if (film.isEmpty()) {
      throw new NotFoundException("Film not found");
    }
    List<Genre> genres = genreRepository.findGenresByFilmId(film.get().getId());

    film.get().setGenres(new LinkedHashSet<>(genres));

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

  public List<Mpa> getAllMpa() {
    return mpaRepository.getAll();
  }

  public Optional<Mpa> getMpaById(long id) {
    Optional<Mpa> result = mpaRepository.findById(id);
    if (result.isEmpty()) {
      throw new NotFoundException("Mpa not found");
    }
    return result;

  }
}
