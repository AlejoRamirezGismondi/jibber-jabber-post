package com.jibberjabberpost.post.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
  Long getUserId(String token);
  String getUserFirstName(String token);
  
  void like(String token, Long postId);
  void unLike(String token, Long postId);
  
  List<Long> getUserFollowing(String token);
}
