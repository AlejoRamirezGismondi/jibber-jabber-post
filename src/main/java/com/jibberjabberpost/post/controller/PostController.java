package com.jibberjabberpost.post.controller;

import com.jibberjabberpost.post.model.Post;
import com.jibberjabberpost.post.service.PostService;
import com.jibberjabberpost.post.service.UserService;
import com.jibberjabberpost.post.service.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/post")
public class PostController {
  
  private final PostService postService;
  private final UserService userService;
  public PostController(PostService postService, UserServiceImpl userService) {
    this.postService = postService;
    this.userService = userService;
  }
  
  @PostMapping()
  public Post createPost(@RequestBody Post post, @RequestHeader("Authorization") String token) {
    post.setAuthorId(userService.getUserId(token));
    return postService.save(post);
  }
  
  @GetMapping(path = "/{id}")
  public Post getPostById(@PathVariable Long id) {
    return postService.getPostById(id);
  }
  
  @GetMapping(path = "/author/{id}")
  public List<Post> getPostByAuthorId(@PathVariable Long id) {
    return postService.getPostsByAuthorId(id);
  }
  
  @GetMapping(path = "/all")
  public List<Post> getAllPosts() {
    return postService.getAll();
  }
  
  @DeleteMapping(path = "/{id}")
  public void deletePost(@PathVariable Long id, @RequestHeader("Authorization") String token) {
    postService.delete(id, userService.getUserId(token));
  }
}
