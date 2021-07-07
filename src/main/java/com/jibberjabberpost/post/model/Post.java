package com.jibberjabberpost.post.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "post")
@Data
public class Post {
  @Id @GeneratedValue
  private Long id;
  private String body, date, firstName;
  private Long authorId;
  @ElementCollection @CollectionTable(name = "likes")
  private List<Long> likeIds = new ArrayList<>();
  
  public void addLike(Long userId) {
    likeIds.add(userId);
  }
  
  public void removeLike(Long userId) {
    likeIds.remove(userId);
  }
}
