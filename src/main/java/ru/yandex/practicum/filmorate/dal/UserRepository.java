package ru.yandex.practicum.filmorate.dal;

import jakarta.validation.Valid;
import java.util.Collection;
import java.util.List;
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
  private static final String FIND_FRIENDS_QUERY = "SELECT * FROM users WHERE id in (select f.friend_id from friends f where f.user_id = ?)";

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

  public void addFriend(Integer userSenderId, Integer userReceiverId) {
    jdbc.update("INSERT INTO friends(user_id, friend_id) VALUES (?, ?)",
        userSenderId, userReceiverId);

    jdbc.update("INSERT INTO friends(friend_id, user_id) VALUES (?, ?)",
        userSenderId, userReceiverId);
  }

  public void removeFriend(int id, int friendId) {
    jdbc.update("DELETE FROM friends WHERE user_id = ? AND friend_id = ?", id, friendId);
  }

  public List<User> getFriends(long id) {
    return findMany(FIND_FRIENDS_QUERY, id);
  }
}
