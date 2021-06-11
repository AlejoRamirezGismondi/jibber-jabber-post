package com.jibberjabberpost.post.model.dto;

import lombok.Data;

@Data
public class PostDTO {
  private Long id;
  private String title, body;
  private Long authorId, likes;
  
  public PostDTO(Long id, String title, String body, Long authorId, Long likes) {
    this.id = id;
    this.title = title;
    this.body = body;
    this.authorId = authorId;
    this.likes = likes;
  }
}
