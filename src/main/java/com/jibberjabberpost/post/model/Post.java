package com.jibberjabberpost.post.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "post")
@Data
public class Post {
  @Id @GeneratedValue
  private Long id;
  private String title, body;
  private Long authorId;
}
