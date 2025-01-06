package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import java.util.Collection;
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
public class UserController {
  private final UserRepository userService;

  public UserController(UserRepository userService) {
    this.userService = userService;
  }

  @PostMapping()
  public User createUser(@Valid @RequestBody User user) {
    return userService.read(userService.create(user));
  }

  @PutMapping()
  public User updateUser(@Valid @RequestBody User user) {
    return userService.update(user);
  }

  @GetMapping()
  public Collection<User> listUser() {
    return userService.getAll();
  }
}
