package com.example.minitest.repository;

import com.example.minitest.model.Post;
import javafx.geometry.Pos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface IPostReponsitory extends PagingAndSortingRepository<Post, Long> {
    Iterable<Post> findAllByTitleContaining(String title);

    Iterable<Post> findAllByOrderByLikes();

    @Query(value = "select * from post order by id desc LIMIT 4", nativeQuery = true)
    Iterable<Post> getTop4New();

    Iterable<Post> findAllByCreateAtBetween(LocalDateTime from, LocalDateTime to);

    Page<Post> findAllByTitleContaining(String title, Pageable pageable);

    Page<Post> findAll(Pageable pageable);

    //- Dùng SDR, Thymeleaf
    //1. Làm giao diện cho chắc năng vừa tìm theo title, vừa tìm theo khoảng thời gian. Nếu thiếu 1 trong 2 thì tìm theo cái kia
    //2. Tìm theo khoảng thời gian và sắp xếp từ cũ đến mới
    //3. Làm giao diện cho chắc năng vừa tìm theo title, vừa tìm theo khoảng thời gian và sắp xếp từ cũ đến mới
    //4. Phân trang 5 sản phẩm 1 trang
    //5. Phân trang 5 sản phẩm 1 trang, sắp xếp từ cũ đến mới
    //6. Tìm kiếm theo title và phân trang 5 sản phẩm 1 trang
}
