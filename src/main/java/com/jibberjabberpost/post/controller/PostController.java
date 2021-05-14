package com.jibberjabberpost.post.controller;

import com.jibberjabberpost.post.model.Post;
import com.jibberjabberpost.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/post")
public class PostController {
  
  @Autowired
  private PostService postService;
  
  @PostMapping()
  public void postPost(@RequestBody Post post) {
    postService.save(post);
  }
  
  
}
