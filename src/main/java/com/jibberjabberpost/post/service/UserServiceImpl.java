package com.jibberjabberpost.post.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    HttpEntity http = tokenToHttp(token);
    final ResponseEntity<String> exchange = restTemplate.exchange(url + "/id", HttpMethod.GET, http, String.class);
    return Long.parseLong(exchange.getBody());
  }
  
  @Override
  public void like(String token, Long postId) {
    final HttpEntity http = tokenToHttp(token);
    restTemplate.exchange(url + "/like/" + postId, HttpMethod.PUT, http, String.class);
  }
  
  @Override
  public void unLike(String token, Long postId) {
    final HttpEntity http = tokenToHttp(token);
    restTemplate.exchange(url + "/unlike/" + postId, HttpMethod.PUT, http, String.class);
  }
  
  @Override
  public List<Long> getUserFollowing(String token) {
    final HttpEntity http = tokenToHttp(token);
    final ResponseEntity<String> exchange = restTemplate.exchange(url + "/following", HttpMethod.GET, http, String.class);
    final List<Long> longs = stringToLongList(exchange.getBody());
    return longs;
  }
  
  private List<Long> stringToLongList(String s) {
    final String[] split = s.replace("[", "").replace("]", "").split(",");
    //in case the user is not following anybody
    if (split[0].equals("")) return new ArrayList<>();
    return Arrays.stream(split).map(Long::parseLong).collect(Collectors.toList());
  }
  
  private HttpEntity tokenToHttp(String token) {
    final HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", token);
    HttpEntity a = new HttpEntity(headers);
    return a;
  }
}
