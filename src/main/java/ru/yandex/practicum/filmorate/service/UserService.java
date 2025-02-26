package ru.yandex.practicum.filmorate.service;

import jakarta.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.dal.UserRepository;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.User;

@Service
public class UserService {

  final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public boolean addFriendToUser(Integer userSenderId, Integer userReceiverId) {
    Optional<User> userSender = userRepository.read(userSenderId);
    if (userSender.isEmpty()) {
      throw new NotFoundException("User with id " + userSenderId + " not found");
    }
    Optional<User> userReceiver = userRepository.read(userReceiverId);
    if (userReceiver.isEmpty()) {
      throw new NotFoundException("User with id " + userReceiverId + " not found");
    }

    userRepository.addFriend(userSenderId, userReceiverId);
    return true;
  }

  public void removeFriendFromUser(int id, int friendId) {
    Optional<User> user = userRepository.read(id);
    if (user.isEmpty()) {
      throw new NotFoundException("User with id " + id + " not found");
    }

    Optional<User> friend = userRepository.read(friendId);
    if (friend.isEmpty()) {
      throw new NotFoundException("User with id " + friendId + " not found");
    }

  }

  public List<Optional<User>> getUserFriends(int id) {
    Optional<User> user = userRepository.read(id);
    if (user.isEmpty()) {
      throw new NotFoundException("User with id " + id + " not found");
    }
    return null;
  }

  public List<Optional<User>> getUsersListById(Collection<Integer> usersId) {
    List<Optional<User>> result = usersId.stream()
        .map(userRepository::read)
        .filter(Objects::nonNull)
        .collect(Collectors.toList());
    return result;
  }

  public List<Optional<User>> getCommonFriends(int id, int otherId) {
    Optional<User> user = userRepository.read(id);
    if (user.isEmpty()) {
      throw new NotFoundException("User with id " + id + " not found");
    }
    Optional<User> otherUser = userRepository.read(otherId);
    if (otherUser.isEmpty()) {
      throw new NotFoundException("User with id " + otherId + " not found");
    }
    //Set<Integer> friendsId = user.get().getFriends();
    //Set<Integer> otherFriendsId = otherUser.get().getFriends();
    //friendsId.retainAll(otherFriendsId);

    return null;
  }

  public Optional<User> update(@Valid User userUpdate) {
    Optional<User> user = userRepository.read(userUpdate.getId());
    if (user.isEmpty()) {
      throw new NotFoundException("User not found");
    }
    return userRepository.update(userUpdate);
  }

  public Collection<User> getAll() {
    return userRepository.getAll();
  }

  public long create(@Valid User user) {
    return userRepository.create(user);
  }

  public Optional<User> read(long id) {
    return userRepository.read(id);
  }
}
