package com.example.minitest.service;

import com.example.minitest.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IGeneralService<T> {
    //    Iterable<T> findAll();
    Page<Post> findAll(Pageable pageable);

    Optional<Post> findById(Long id);

    void save(T t);

    void remove(Long id);
}
