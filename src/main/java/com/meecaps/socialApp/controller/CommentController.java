package com.meecaps.socialApp.controller;


import com.meecaps.socialApp.request.CommentRequest;
import com.meecaps.socialApp.response.CommentResponse;
import com.meecaps.socialApp.serviceImplements.CommentServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {


    private final CommentServiceImpl commentServiceImpl;

    public CommentController(CommentServiceImpl commentServiceImpl) {
        this.commentServiceImpl = commentServiceImpl;
    }


    @PostMapping("/create")
    public CommentResponse createComment(@RequestBody
                                 CommentRequest request){

        return commentServiceImpl.createComment(request);
    }

    @GetMapping("/get")
    public List<CommentResponse> getAllComment(){

        return commentServiceImpl.getAllComment();
    }

    @GetMapping("/get/{id}")
    public CommentResponse getByID(@PathVariable Long id){
        return commentServiceImpl.getById(id);
    }

    @PutMapping("update/{id}")
    public CommentResponse updateComment(@PathVariable Long id,
                                 @RequestBody CommentRequest request){

        return commentServiceImpl.updateComment(id,request);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteComment(@PathVariable Long id)
    {
        return commentServiceImpl.deleteComment(id);
    }
}
