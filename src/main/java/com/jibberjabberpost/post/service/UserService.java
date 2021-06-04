package com.jibberjabberpost.post.service;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
  Long getUserId(String token);
}
