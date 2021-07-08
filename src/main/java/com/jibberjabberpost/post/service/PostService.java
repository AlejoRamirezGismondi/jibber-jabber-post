package com.jibberjabberpost.post.service;


import com.jibberjabberpost.post.factory.PostFactory;
import com.jibberjabberpost.post.model.Post;
import com.jibberjabberpost.post.model.dto.PostDTO;
import com.jibberjabberpost.post.repository.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {
  
  private final PostRepository postRepository;
  private final UserService userService;
  public PostService(PostRepository postRepository, UserService userService) {
    this.postRepository = postRepository;
    this.userService = userService;
  }
  
  public Post save(Post post, String token) {
    post.setAuthorId(userService.getUserId(token));
    post.setUserName(userService.getUserUserName(token));
    return postRepository.save(post);
  }
  
  public Post getPostById(Long id) {
    final Optional<Post> optional = postRepository.findById(id);
    if (optional.isPresent()) return optional.get();
    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found");
  }
  
  public List<Post> getAll() {
    return postRepository.findAll();
  }
  
  public List<Post> getPostsByAuthorId(Long id) {
    return postRepository.findPostByAuthorId(id);
  }
  
  public void delete(Long id, String token) {
    final Long userId = userService.getUserId(token);
    final Optional<Post> optional = postRepository.findById(id);
    if (!optional.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found");
    if (!userId.equals(optional.get().getAuthorId())) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User is not the owner");
    postRepository.deleteById(id);
  }
  
  public void like(Long postId, String token) {
    final Long userId = userService.getUserId(token);
    final Post post = getPostById(postId);
    post.addLike(userId);
    postRepository.save(post);
    userService.like(token, postId);
  }
  
  public void unLike(Long postId, String token) {
    final Long userId = userService.getUserId(token);
    final Post post = getPostById(postId);
    post.removeLike(userId);
    postRepository.save(post);
    userService.unLike(token, postId);
  }
  
  public List<Post> getPostsByFollowing(String token) {
    final List<Long> following = userService.getUserFollowing(token);
    return following.stream().flatMap(f -> getPostsByAuthorId(f).stream()).collect(Collectors.toList());
  }
  
  public List<PostDTO> toDto(List<Post> posts, String token) {
    final Long userId = userService.getUserId(token);
    return posts.stream().map(e -> toDto(e, userId)).collect(Collectors.toList());
  }
  
  public PostDTO toDto(Post post, String token) {
    return toDto(post, userService.getUserId(token));
  }
  
  public PostDTO toDto(Post post, Long userId) {
    return PostFactory.postToDTO(post, userId);
  }
  
  public List<Post> getPostsByToken(String token) {
    final Long userId = userService.getUserId(token);
    return getPostsByAuthorId(userId);
  }
}
