package com.jibberjabberpost.post.repository;


import com.jibberjabberpost.post.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
