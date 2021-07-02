package com.jibberjabberpost.post.model.dto;

import lombok.Data;

@Data
public class PostDTO {
  private Long id;
  private String title, body, date, firstName;
  private Long authorId, likes;
  
  public PostDTO(String firstName, String date, Long id, String title, String body, Long authorId, Long likes) {
    this.firstName = firstName;
    this.date = date;
    this.id = id;
    this.title = title;
    this.body = body;
    this.authorId = authorId;
    this.likes = likes;
  }
}
