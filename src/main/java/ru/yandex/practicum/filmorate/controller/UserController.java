package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.model.User;

@RestController
@RequestMapping("/users")
public class UserController {

  @PostMapping()
  public User createUser(@Valid @RequestBody User user) {
    return user;
  }

  @PutMapping()
  public User updateUser(@Valid @RequestBody User user) {
    return user;
  }

  @GetMapping()
  public Collection<User> listUser() {
    return new ArrayList<>();
  }
}
