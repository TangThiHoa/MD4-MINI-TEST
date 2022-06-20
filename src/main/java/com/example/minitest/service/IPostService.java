package com.example.minitest.service;

import com.example.minitest.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface IPostService extends IGeneralService<Post> {
    Iterable<Post> findAllByTitleContaining(String title);

    Iterable<Post> findAllByOrderByLikes();

    Iterable<Post> getTop4New();

    Iterable<Post> findAllByCreateAtBetween(LocalDateTime from, LocalDateTime to);

    Page<Post> findAllByTitleContaining(String title, Pageable pageable);
}
