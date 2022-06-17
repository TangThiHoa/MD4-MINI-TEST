package com.example.minitest.service;

import com.example.minitest.model.Post;
import org.springframework.data.jpa.repository.Query;

public interface IPostService extends IGeneralService<Post> {
    Iterable<Post> findAllByTitleContaining(String title);

    Iterable<Post> findAllByOrderByLikes();

    Iterable<Post> getTop4New();
}
