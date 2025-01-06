package ru.yandex.practicum.filmorate.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract class CrudRepository<T> {

  private final Map<Integer, T> store = new HashMap<>();
  private int counter = 1;

  public abstract int getEntityId(T t);

  public abstract int setEntityId(int id, T t);

  public int getCounter() {
    return counter++;
  }

  public int create(T t) {
    setEntityId(getCounter(), t);
    store.put(getEntityId(t), t);
    return getEntityId(t);
  }

  public T update(T t) {
    store.put(getEntityId(t), t);
    return t;
  }

  public T read(int id) {
    return store.get(id);
  }

  public T delete(int id) {
    return store.remove(id);
  }

  public List<T> getAll() {
    return new ArrayList<>(store.values());
  }
}
