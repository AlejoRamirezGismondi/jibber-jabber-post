package com.jibberjabberpost.post.factory;

import com.jibberjabberpost.post.model.Post;
import com.jibberjabberpost.post.model.dto.PostDTO;

public class PostFactory {
  
  public static PostDTO postToDTO(Post post) {
    return new PostDTO(
            post.getDate(),
            post.getId(),
            post.getTitle(),
            post.getBody(),
            post.getAuthorId(),
            (long) post.getLikeIds().size()
    );
  }
  
}
