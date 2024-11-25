package com.ift2255a24;

import java.util.List;
import java.util.Optional;

public interface IRepository<T> {
    Project save(Project project);
    List<Project> getAll();
    Optional<Project> get(int id);
    boolean delete(int id);
}

