package com.example.minitest.repository;

import com.example.minitest.model.Post;
import javafx.geometry.Pos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IPostReponsitory extends JpaRepository<Post, Long> {
    Iterable<Post> findAllByTitleContaining(String title);

    Iterable<Post> findAllByOrderByLikes();

    @Query(value = "select * from post order by id desc LIMIT 4", nativeQuery = true)
    Iterable<Post> getTop4New();
}
