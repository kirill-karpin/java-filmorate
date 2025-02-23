package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import java.util.Collection;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping()
  public User createUser(@Valid @RequestBody User user) {
    log.info("Create user: " + user);
    return userService.read(userService.create(user));
  }

  @PutMapping()
  public User updateUser(@Valid @RequestBody User user) {
    log.info("Update user: " + user);
    return userService.update(user);
  }

  @GetMapping("")
  public Collection<User> listUser() {
    return userService.getAll();
  }

  @PutMapping("/{id}/friends/{friendId}")
  public void addFriend(@PathVariable int id, @PathVariable int friendId) {
    log.info("Add friend: " + id + " " + friendId);
    userService.addFriendToUser(id, friendId);
  }

  @DeleteMapping("/{id}/friends/{friendId}")
  public void deleteFriend(@PathVariable int id, @PathVariable int friendId) {
    log.info("Delete friend: " + id + " " + friendId);
    userService.removeFriendFromUser(id, friendId);
  }

  @GetMapping("/{id}/friends")
  public List<User> getUseFriends(@PathVariable int id) {
    log.info("Get friends: " + id);
    return userService.getUserFriends(id);
  }

  @GetMapping("/{id}/friends/common/{otherId}")
  public List<User> getCommonFriends(@PathVariable int id, @PathVariable int otherId) {
    log.info("Get common friends: " + id + " " + otherId);
    return userService.getCommonFriends(id, otherId);
  }


}
