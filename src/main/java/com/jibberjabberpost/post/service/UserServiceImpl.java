package com.jibberjabberpost.post.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImpl implements UserService {
  
  private final RestTemplate restTemplate;
  
  @Value("${microservice.user.base-url}")
  private String url;
  
  public UserServiceImpl(RestTemplateBuilder rtb) {
    this.restTemplate = rtb.build();
  }
  
  @Override
  public Long getUserId(String token) {
    final HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", token);
    HttpEntity a = new HttpEntity(headers);
    final ResponseEntity<String> exchange = restTemplate.exchange(url + "/id", HttpMethod.GET, a, String.class);
    return Long.parseLong(exchange.getBody());
  }
}
