package ru.yandex.practicum.filmorate.dal;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.film.Genre;

@Component
public class GenreRepository extends BaseRepository<Genre> {

  private static final String FIND_ALL_QUERY = "SELECT * FROM genres";
  private static final String FIND_BY_ID_QUERY = "SELECT * FROM genres WHERE id = ?";
  private static final String FIND_GENRES_BY_FILM_ID_QUERY = "select distinct g.id, g.name from genres g join film_genre fg on g.id = fg.genre_id where fg.film_id = ?";
  private static final String FIND_GENRES_BY_IDS = "SELECT * FROM genres WHERE id in (%s)";
  private static final String ADD_GENRES_TO_FILM_QUERY = "INSERT INTO film_genre (film_id, genre_id) VALUES (?, ?)";

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
    jdbc.update(ADD_GENRES_TO_FILM_QUERY, id, id1);
  }

  public List<Genre> findGenresByFilmId(long id) {
    var result = findMany(FIND_GENRES_BY_FILM_ID_QUERY, id);
    return result;
  }

  public boolean batchUpdateFilmGenres(Long filmId, List<Genre> genres) {
    int[] result = jdbc.batchUpdate(ADD_GENRES_TO_FILM_QUERY, new BatchPreparedStatementSetter() {
      @Override
      public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
        preparedStatement.setLong(1, filmId);
        preparedStatement.setLong(2, genres.get(i).getId());
      }

      @Override
      public int getBatchSize() {
        return genres.size();
      }
    });
    return result.length == genres.size();
  }

  public List<Genre> listGenresByIds(List<Long> ids) {
    String inSql = String.join(",", Collections.nCopies(ids.size(), "?"));
    var result = jdbc.query(String.format(FIND_GENRES_BY_IDS, inSql), mapper, ids.toArray());
    return result;
  }
}
