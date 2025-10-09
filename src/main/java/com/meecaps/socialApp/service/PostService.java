package com.meecaps.socialApp.service;

import com.meecaps.socialApp.request.PostRequest;
import com.meecaps.socialApp.response.PostResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PostService {

    List<PostResponse> getAllPost();

    PostResponse createPost(PostRequest postRequest);

    PostResponse updatePost(Long id, PostRequest request);

    String deletePost(Long id);
}