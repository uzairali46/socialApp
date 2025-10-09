package com.meecaps.socialApp.service;

import com.meecaps.socialApp.request.CommentRequest;
import com.meecaps.socialApp.response.CommentResponse;

import java.util.List;

public interface CommentsService {

    CommentResponse createComment(CommentRequest request);

    List<CommentResponse> getAllComment();

    CommentResponse getById(Long id);

    CommentResponse updateComment(Long id,
                                  CommentRequest request);

    String deleteComment(Long id);
}
