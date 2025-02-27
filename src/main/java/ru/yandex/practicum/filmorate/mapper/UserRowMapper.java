package ru.yandex.practicum.filmorate.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.User;

@Component
public class UserRowMapper implements RowMapper<User> {

  @Override
  public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
    User user = new User();
    user.setId(resultSet.getInt("id"));
    user.setLogin(resultSet.getString("login"));
    user.setName(resultSet.getString("name"));
    user.setEmail(resultSet.getString("email"));
    user.setBirthday(resultSet.getDate("birthday").toLocalDate());
    return user;
  }
}
