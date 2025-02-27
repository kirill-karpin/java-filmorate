package ru.yandex.practicum.filmorate.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.film.Mpa;

@Component
public class MpaRowMapper implements RowMapper<Mpa> {

  @Override
  public Mpa mapRow(ResultSet resultSet, int rowNum) throws SQLException {
    Mpa mpa = new Mpa();
    mpa.setId(resultSet.getLong("id"));
    mpa.setName(resultSet.getString("name"));
    return mpa;
  }
}
