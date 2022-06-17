package com.example.minitest.service;

import com.example.minitest.model.Post;

import java.util.Optional;

public interface IGeneralService <T>{
    Iterable<T> findAll();

    Optional<Post> findById(Long id);

    void save(T t);

    void remove(Long id);
}
