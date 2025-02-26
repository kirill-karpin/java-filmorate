package ru.yandex.practicum.filmorate.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.film.Genre;

@Component
public class GenreRowMapper implements RowMapper<Genre> {

  @Override
  public Genre mapRow(ResultSet resultSet, int rowNum) throws SQLException {
    Genre genre = new Genre();
    genre.setId(resultSet.getLong("id"));
    genre.setName(resultSet.getString("name"));
    return genre;
  }
}
