package ru.yandex.practicum.filmorate.dal;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.film.Film;

@Component
public class FilmRepository extends BaseRepository<Film> {

  private static final String INSERT_QUERY =
      "insert into films(name, description, duration, release_date)" + " values (?, ?, ?, ?)";
  private static final String FIND_BY_ID_QUERY = "SELECT f.*, fm.mpa_id as mpa_id FROM films f JOIN film_mpa fm ON fm.film_id =f.id WHERE f.id = ?";
  private static final String UPDATE_QUERY = "UPDATE films SET name = ?, description = ?, duration = ?, release_date = ? WHERE id = ?";
  private static final String FIND_ALL_QUERY = "SELECT f.*, m.* FROM films f JOIN film_mpa fm ON fm.film_id =f.id JOIN mpa m ON fm.mpa_id = m.id";
  private static final String DELETE_QUERY = "DELETE FROM films WHERE id = ?";
  private static final String MOST_POPULAR_FILM_QUERY = "SELECT f.*, fm.mpa_id FROM films f JOIN film_mpa fm ON fm.film_id = f.id JOIN mpa m ON fm.mpa_id = m.id left join (select f.film_id, count(*) as \"total\" from FILM_LIKES f group by f.film_id) cnt on cnt.film_id = f.id order by cnt.\"total\" desc limit ?\n";


  public FilmRepository(JdbcTemplate jdbc, RowMapper<Film> mapper) {
    super(jdbc, mapper, Film.class);
  }

  public Collection<Film> getPopularFilms(int count) {
    return getMostPopular(count);
  }

  public long create(Film film) {
    return insert(INSERT_QUERY, film.getName(), film.getDescription(), film.getDuration(),
        film.getReleaseDate());
  }

  public Optional<Film> update(Film film) {
    long id = insert(UPDATE_QUERY, film.getName(), film.getDescription(), film.getDuration(),
        film.getReleaseDate(), film.getId());
    return findOne(FIND_BY_ID_QUERY, id);
  }

  public Optional<Film> read(long id) {
    return findOne(FIND_BY_ID_QUERY, id);
  }

  public Film delete(long id) {
    super.delete(DELETE_QUERY, id);
    return null;
  }

  public List<Film> getAll() {
    return findMany(FIND_ALL_QUERY);
  }

  public List<Film> getMostPopular(int count) {
    var result = findMany(MOST_POPULAR_FILM_QUERY, count);
    return result;
  }
}
