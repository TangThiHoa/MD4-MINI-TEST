package com.example.minitest.controller;

import com.example.minitest.model.Post;
import com.example.minitest.repository.IPostReponsitory;
import com.example.minitest.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/posts")
public class PostController {
    @Autowired
    IPostService postService;
    @GetMapping("")
    public ModelAndView listPost(@RequestParam("search") Optional<String> search) {
        Iterable<Post> posts;
        if(search.isPresent()){   //check tồn tại của optuon
            posts = postService.findAllByTitleContaining(search.get());
        } else {
            posts = postService.findAllByOrderByLikes();
        }
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("post", posts);
        return modelAndView;
    }
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
}
