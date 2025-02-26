package ru.yandex.practicum.filmorate.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.film.Like;

@Component
public class LikeRowMapper implements RowMapper<Like> {

  @Override
  public Like mapRow(ResultSet resultSet, int rowNum) throws SQLException {
    Like like = new Like();
    like.setId(resultSet.getLong("id"));
    return like;
  }
}
