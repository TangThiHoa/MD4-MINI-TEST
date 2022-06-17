package com.example.minitest.service.impl;

import com.example.minitest.model.Post;
import com.example.minitest.repository.IPostReponsitory;
import com.example.minitest.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService implements IPostService {
    @Autowired
    IPostReponsitory postReponsitory;

    @Override
    public Iterable<Post> findAll() {
        return postReponsitory.findAll();
    }

    @Override
    public Optional<Post> findById(Long id) {
        return postReponsitory.findById(id);
    }

    @Override
    public void save(Post post) {
        postReponsitory.save(post);


    }

    @Override
    public void remove(Long id) {
        postReponsitory.deleteById(id);

    }

    @Override
    public Iterable<Post> findAllByTitleContaining(String title) {
        return postReponsitory.findAllByTitleContaining(title);
    }

    @Override
    public Iterable<Post> findAllByOrderByLikes() {
        return postReponsitory.findAllByOrderByLikes();
    }

    @Override
    public Iterable<Post> getTop4New() {
        return postReponsitory.getTop4New();
    }


}
