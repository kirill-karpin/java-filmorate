package ru.yandex.practicum.filmorate.dal;

import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.film.Mpa;

@Component
public class MpaRepository extends BaseRepository<Mpa> {

  private static final String FIND_ALL_QUERY = "SELECT * FROM mpa";
  private static final String FIND_BY_ID_QUERY = "SELECT * FROM mpa WHERE id = ?";
  private static final String CREATE_FILM_MPA = "insert into film_mpa (film_id, mpa_id) values (?, ?)";

  public MpaRepository(JdbcTemplate jdbc, RowMapper<Mpa> mapper) {
    super(jdbc, mapper, Mpa.class);
  }


  public List<Mpa> getAll() {
    return findMany(FIND_ALL_QUERY);
  }

  public Optional<Mpa> findById(long id) {
    return findOne(FIND_BY_ID_QUERY, id);
  }

  public long addFilmMpa(long id, Long mpaId) {
    return insert(CREATE_FILM_MPA, id, mpaId);
  }
}
