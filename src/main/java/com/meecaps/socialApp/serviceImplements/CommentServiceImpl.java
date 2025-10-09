package com.meecaps.socialApp.serviceImplements;

import com.meecaps.socialApp.entity.Comment;
import com.meecaps.socialApp.entity.Post;
import com.meecaps.socialApp.entity.User;
import com.meecaps.socialApp.repository.CommentRepository;
import com.meecaps.socialApp.repository.PostRepository;
import com.meecaps.socialApp.repository.UserRepository;
import com.meecaps.socialApp.request.CommentRequest;
import com.meecaps.socialApp.response.CommentResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository,
                              UserRepository userRepository,
                              PostRepository postRepository ){
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;


    }

    public CommentResponse createComment(CommentRequest request){
        User user = userRepository.findById(request.getUserId()).orElseThrow(
                ()-> new RuntimeException("user not found"));

        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(()-> new RuntimeException("post not found"));

        Comment comment = new Comment();
        comment.setCommentString(request.getCommentString());
        comment.setAuthor(user);
        comment.setPostID(post);
        Comment save = commentRepository.save(comment);
        return new CommentResponse(save);

    }

    public List<CommentResponse> getAllComment(){
        List<Comment> all = commentRepository.findAll();

        return all.stream().map((CommentResponse::new)).toList();

    }
    public CommentResponse getById(Long id){
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("not found")

        );
        return new CommentResponse(comment);

    }

    public CommentResponse updateComment(Long id,
                                         CommentRequest request){
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("not found")
        );
        comment.setCommentString(request.getCommentString());
        Comment save = commentRepository.save(comment);
        return new CommentResponse(save);

    }

    public String deleteComment(Long id){
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));

        commentRepository.delete(comment);
        return "deleted";
    }



}
