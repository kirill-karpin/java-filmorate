package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import java.util.Collection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserRepository;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

  private final UserRepository userRepository;

  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @PostMapping()
  public User createUser(@Valid @RequestBody User user) {
    log.info("Create user: " + user);
    return userRepository.read(userRepository.create(user));
  }

  @PutMapping()
  public User updateUser(@Valid @RequestBody User user) {
    log.info("Update user: " + user);
    return userRepository.update(user);
  }

  @GetMapping()
  public Collection<User> listUser() {
    return userRepository.getAll();
  }

  @PutMapping("/users/{id}/friends/{friendId}")
  public void addFriend(@RequestBody User user) {
    log.info("Add friend: " + user);
  }

  @DeleteMapping("/users/{id}/friends/{friendId}")
  public void deleteFriend(@RequestBody User user) {
    log.info("Delete friend: " + user);
  }

  @GetMapping("/users/{id}/friends")
  public void getFriends(@RequestBody User user) {
    log.info("Get friends: " + user);
  }
  @GetMapping(" /users/{id}/friends/common/{otherId}")
  public void getCommonFriends(@RequestBody User user) {
    log.info("Get common friends: " + user);
  }


}
