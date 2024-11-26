package com.ift2255a24;

import java.util.List;
import java.util.Optional;

public interface IRepository<T> {
    T save(T project);
    List<T> getAll();
    Optional<T> get(int id);
    boolean delete(int id);
}

