package ru.yandex.practicum.filmorate.service;

import jakarta.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.User;

@Service
public class UserService {

  final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public boolean addFriendToUser(Integer userSenderId, Integer userReceiverId) {
    User userSender = userRepository.read(userSenderId);
    if (userSender == null) {
      throw new NotFoundException("User with id " + userSenderId + " not found");
    }
    User userReceiver = userRepository.read(userReceiverId);
    if (userReceiver == null) {
      throw new NotFoundException("User with id " + userReceiverId + " not found");
    }
    userReceiver.addFriend(userSenderId);

    userSender.addFriend(userReceiverId);
    return true;
  }

  public void removeFriendFromUser(int id, int friendId) {
    User user = userRepository.read(id);
    if (user == null) {
      throw new NotFoundException("User with id " + id + " not found");
    }

    User friend = userRepository.read(friendId);
    if (friend == null) {
      throw new NotFoundException("User with id " + friendId + " not found");
    }

    user.removeFriend(friendId);
    friend.removeFriend(id);
    userRepository.update(user);
    userRepository.update(friend);
  }

  public List<User> getUserFriends(int id) {
    User user = userRepository.read(id);
    if (user == null) {
      throw new NotFoundException("User with id " + id + " not found");
    }
    Set<Integer> friendsId = user.getFriends();
    return getUsersListById(friendsId);
  }

  public List<User> getUsersListById(Collection<Integer> usersId) {
    List<User> result = usersId.stream()
        .map(userRepository::read)
        .filter(Objects::nonNull)
        .collect(Collectors.toList());
    return result;
  }

  public List<User> getCommonFriends(int id, int otherId) {
    User user = userRepository.read(id);
    if (user == null) {
      throw new NotFoundException("User with id " + id + " not found");
    }
    User otherUser = userRepository.read(otherId);
    if (otherUser == null) {
      throw new NotFoundException("User with id " + otherId + " not found");
    }
    Set<Integer> friendsId = user.getFriends();
    Set<Integer> otherFriendsId = otherUser.getFriends();
    friendsId.retainAll(otherFriendsId);

    return getUsersListById(friendsId);
  }

  public User update(@Valid User userUpdate) {
    User user = userRepository.read(userUpdate.getId());
    if (user == null) {
      throw new NotFoundException("Film not found");
    }
    return userRepository.update(userUpdate);
  }

  public Collection<User> getAll() {
    return userRepository.getAll();
  }

  public int create(@Valid User user) {
    return userRepository.create(user);
  }

  public User read(int id) {
    return userRepository.read(id);
  }
}
