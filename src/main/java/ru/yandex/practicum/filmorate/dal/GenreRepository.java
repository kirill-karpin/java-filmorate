package ru.yandex.practicum.filmorate.dal;

import java.util.Collection;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.film.Genre;

@Component
public class GenreRepository extends BaseRepository<Genre> {

  private static final String FIND_ALL_QUERY = "SELECT * FROM genres";
  private static final String FIND_BY_ID_QUERY = "SELECT * FROM genres WHERE id = ?";


  public GenreRepository(JdbcTemplate jdbc, RowMapper<Genre> mapper) {
    super(jdbc, mapper, Genre.class);
  }

  public Collection<Genre> getAll() {
    return findMany(FIND_ALL_QUERY);
  }

  public Optional<Genre> findById(long id) {
    return findOne(FIND_BY_ID_QUERY, id);
  }

  public void addFilmGenre(long id, Long id1) {
    jdbc.update("INSERT INTO film_genre (film_id, genre_id) VALUES (?, ?)", id, id1);
  }
}
