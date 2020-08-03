package com.example.project.service;

import java.util.List;
import java.util.Optional;

public interface CoreService<T> {
    List<T> getAll();
    Optional<T> getById(Long id);
    T save(T t);
    T updateOrSave(T t);
    void delete(Long id);
}
