package com.jibberjabberpost.post.controller;

import com.jibberjabberpost.post.model.Post;
import com.jibberjabberpost.post.model.dto.PostDTO;
import com.jibberjabberpost.post.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/post")
public class PostController {
  
  private final PostService postService;
  public PostController(PostService postService) {
    this.postService = postService;
  }
  
  @PostMapping()
  public Post createPost(@RequestBody Post post, @RequestHeader("Authorization") String token) {
    return postService.save(post, token);
  }
  
  @GetMapping(path = "/{id}")
  public Post getPostById(@PathVariable Long id) {
    return postService.getPostById(id);
  }
  
  @GetMapping(path = "/myPosts")
  public List<Post> getMyPosts(@RequestHeader("Authorization") String token) {
    return postService.getPostsByToken(token);
  }
  
  @GetMapping(path = "/all")
  public List<Post> getAllPosts() {
    return postService.getAll();
  }
  
  @DeleteMapping(path = "/{id}")
  public void deletePost(@PathVariable Long id, @RequestHeader("Authorization") String token) {
    postService.delete(id, token);
  }
  
  @PutMapping(path = "/like/{id}")
  public void likePost(@PathVariable Long id, @RequestHeader("Authorization") String token) {
    postService.like(id, token);
  }
  
  @PutMapping(path = "/unlike/{id}")
  public void unLikePost(@PathVariable Long id, @RequestHeader("Authorization") String token) {
    postService.unLike(id, token);
  }
  
  @GetMapping(path = "/following")
  public List<PostDTO> getPostsByFollowing(@RequestHeader("Authorization") String token) {
    return postService.toDto(postService.getPostsByFollowing(token));
  }
}
