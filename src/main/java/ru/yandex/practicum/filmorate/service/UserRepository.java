package ru.yandex.practicum.filmorate.service;

import jakarta.validation.Valid;
import java.util.Collection;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.User;

@Component
public class UserRepository extends BaseRepository<User> {

  private static final String INSERT_QUERY =
      "insert into users(name, login, email, birthday)" + " values (?, ?, ?, ?)";
  private static final String FIND_BY_ID_QUERY = "SELECT * FROM users WHERE id = ?";
  private static final String UPDATE_QUERY = "UPDATE users SET name = ?, email = ?, login = ?, birthday = ? WHERE id = ?";
  private static final String FIND_ALL_QUERY = "SELECT * FROM users";
  private static final String DELETE_QUERY = "DELETE FROM users WHERE id = ?";

  public UserRepository(JdbcTemplate jdbc, RowMapper<User> mapper) {
    super(jdbc, mapper, User.class);
  }


  public Optional<User> read(long userSenderId) {
    return findOne(FIND_BY_ID_QUERY, userSenderId);
  }

  public Optional<User> update(User user) {
    long id = insert(UPDATE_QUERY,
        user.getName(), user.getEmail(), user.getLogin(), user.getBirthday(), user.getId());
    return findOne(FIND_BY_ID_QUERY, id);
  }

  public Collection<User> getAll() {
    return findMany(FIND_ALL_QUERY);
  }

  public long create(@Valid User user) {
    return insert(INSERT_QUERY, user.getName(), user.getLogin(), user.getEmail(),
        user.getBirthday());
  }
}
