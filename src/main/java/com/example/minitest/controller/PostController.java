package com.example.minitest.controller;

import com.example.minitest.model.Post;
import com.example.minitest.repository.IPostReponsitory;
import com.example.minitest.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/posts")
public class PostController {
    @Autowired
    IPostService postService;
//    @GetMapping("")
//    public ModelAndView listPost(@RequestParam("search") Optional<String> search) {
//        Iterable<Post> posts;
//        if(search.isPresent()){   //check tồn tại của optuon
//            posts = postService.findAllByTitleContaining(search.get());
//        } else {
//            posts = postService.findAllByOrderByLikes();
//        }
//        ModelAndView modelAndView = new ModelAndView("list");
//        modelAndView.addObject("post", posts);
//        return modelAndView;
//    }
    @GetMapping("/edit/{id}")
    public ModelAndView editForm(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        Post post = postService.findById(id).get();
        modelAndView.addObject("post", post);
        return modelAndView;
    }

    @PostMapping("/update")
    public ModelAndView update(Post post) {
        postService.save(post);
        ModelAndView modelAndView = new ModelAndView("redirect:/posts");
        return modelAndView;

    }
    @GetMapping("/top4New")
    public ModelAndView top4New() {
        ModelAndView modelAndView = new ModelAndView("new");
        modelAndView.addObject("name",postService.getTop4New());
        return modelAndView;
    }
    @GetMapping("/search")
    public ModelAndView findBetwent(@RequestParam LocalDateTime from, @RequestParam LocalDateTime to) {
        ModelAndView modelAndView = new ModelAndView("search");
        modelAndView.addObject("post",postService.findAllByCreateAtBetween(from,to));
        return modelAndView;
    }
    @GetMapping("/create-post")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("post" , new Post());
        return modelAndView;
    }

    @PostMapping("/create-post")
    public ModelAndView savePost(@ModelAttribute("post") Post post) {
        post.setCreateAt(LocalDateTime.now());
        postService.save(post);
        ModelAndView modelAndView = new ModelAndView("redirect:/posts");
        modelAndView.addObject("post", new Post());
        modelAndView.addObject("message", "Create successfully");
        return modelAndView;
    }
    @GetMapping("/posts")
    public ModelAndView listCustomers(Pageable pageable){
        Page<Post> postPage = postService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("posts", postPage);
        return modelAndView;
    }

    @GetMapping("")
    public ModelAndView pageable(@RequestParam("search") Optional<String> search, Pageable pageable){
        Page<Post> posts;
        if(search.isPresent()){
            posts = postService.findAllByTitleContaining(search.get(), pageable);
        } else {
            posts = postService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("posts", posts);
        return modelAndView;
    }
}
