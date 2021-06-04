package com.jibberjabberpost.post.repository;


import com.jibberjabberpost.post.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
  List<Post> findPostByAuthorId(Long id);
}
