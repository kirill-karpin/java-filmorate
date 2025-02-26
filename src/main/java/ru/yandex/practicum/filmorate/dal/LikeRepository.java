package ru.yandex.practicum.filmorate.dal;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.film.Like;

@Component
public class LikeRepository extends BaseRepository<Like> {

  private static final String ADD_LIKE =
      "insert into film_likes(user_id, film_id)" + " values (?, ?)";
  private static final String REMOVE_LIKE =
      "delete from film_likes where user_id = ? and film_id = ?";


  public LikeRepository(JdbcTemplate jdbc, RowMapper<Like> mapper) {
    super(jdbc, mapper, Like.class);
  }

  public void addLike(int userId, int filmId) {
    jdbc.update(ADD_LIKE, userId, filmId);
  }

  public void removeLike(int userId, int filmId) {
    jdbc.update(REMOVE_LIKE, userId, filmId);
  }
}
