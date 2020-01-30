package com.avenga.a360.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T> {

    void save(T t);

//    Optional<T> findById (Long id);
}
