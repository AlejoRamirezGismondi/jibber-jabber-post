package com.jibberjabberpost.post.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "post")
@Data
public class Post {
  @Id @GeneratedValue
  private Long id;
  @Column(name = "title")
  private String title;
  @Column(name = "author")
  private String author;
  @Column(name = "body")
  private String body;
}
