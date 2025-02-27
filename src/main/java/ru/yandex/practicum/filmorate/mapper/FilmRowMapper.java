package ru.yandex.practicum.filmorate.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.film.Film;
import ru.yandex.practicum.filmorate.model.film.Mpa;

@Component
public class FilmRowMapper implements RowMapper<Film> {

  @Override
  public Film mapRow(ResultSet resultSet, int rowNum) throws SQLException {
    Film film = new Film();
    film.setId(resultSet.getInt("id"));
    film.setName(resultSet.getString("name"));
    film.setReleaseDate(resultSet.getDate("release_date").toLocalDate());
    film.setDescription(resultSet.getString("description"));
    film.setDuration(Duration.ZERO.plusSeconds(resultSet.getInt("duration")));
    Mpa mpa = new Mpa();
    mpa.setId(resultSet.getLong("mpa.id"));
    mpa.setName(resultSet.getString("mpa.name"));
    film.setMpa(mpa);

    return film;
  }
}
