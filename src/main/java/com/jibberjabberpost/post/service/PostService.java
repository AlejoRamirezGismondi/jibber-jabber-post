package com.jibberjabberpost.post.service;


import com.jibberjabberpost.post.model.Post;
import com.jibberjabberpost.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
  
  @Autowired
  private PostRepository postRepository;
  
  
  public Post save(Post post) {
    return postRepository.save(post);
  }
  
  
}
