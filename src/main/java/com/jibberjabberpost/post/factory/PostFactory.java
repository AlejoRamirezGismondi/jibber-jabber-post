package com.jibberjabberpost.post.factory;

import com.jibberjabberpost.post.model.Post;
import com.jibberjabberpost.post.model.dto.PostDTO;

public class PostFactory {
  
  public static PostDTO postToDTO(Post post, Long userId) {
    return new PostDTO(
            post.getUserName(),
            post.getDate(),
            post.getId(),
            post.getBody(),
            post.getAuthorId(),
            (long) post.getLikeIds().size(),
            post.getLikeIds().contains(userId)
    );
  }
  
}
