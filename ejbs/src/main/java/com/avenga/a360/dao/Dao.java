package com.avenga.a360.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    Optional<T> get(long id);

    List<T> getAll();

    void save(T t);

    void delete(T t);

}
