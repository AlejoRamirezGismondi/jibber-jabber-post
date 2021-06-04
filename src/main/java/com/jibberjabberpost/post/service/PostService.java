package com.jibberjabberpost.post.service;


import com.jibberjabberpost.post.model.Post;
import com.jibberjabberpost.post.repository.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
  
  private final PostRepository postRepository;
  
  public PostService(PostRepository postRepository) {
    this.postRepository = postRepository;
  }
  
  public Post save(Post post) {
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
  
  public void delete(Long id, Long userId) {
    final Optional<Post> optional = postRepository.findById(id);
    if (!optional.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found");
    if (!userId.equals(optional.get().getAuthorId())) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User is not the owner");
    postRepository.deleteById(id);
  }
}
