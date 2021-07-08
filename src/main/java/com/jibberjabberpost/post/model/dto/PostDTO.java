package com.jibberjabberpost.post.model.dto;

import lombok.Data;

@Data
public class PostDTO {
  private Long id;
  private String body, date, userName;
  private Long authorId, likes;
  private boolean likedByUser;
  
  public PostDTO(String userName, String date, Long id, String body, Long authorId, Long likes, boolean likedByUser) {
    this.userName = userName;
    this.date = date;
    this.id = id;
    this.body = body;
    this.authorId = authorId;
    this.likes = likes;
    this.likedByUser = likedByUser;
  }
}
