package ru.yandex.practicum.filmorate.service;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.User;

@Component
public class UserRepository extends CrudRepository<User> {

  @Override
  public int getEntityId(User user) {
    return user.getId();
  }

  @Override
  public int setEntityId(int id, User user) {
    user.setId(id);
    return id;
  }
}
