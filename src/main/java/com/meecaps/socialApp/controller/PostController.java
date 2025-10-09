package com.meecaps.socialApp.controller;

import com.meecaps.socialApp.request.PostRequest;
import com.meecaps.socialApp.response.PostResponse;
import com.meecaps.socialApp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
@Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }


    @GetMapping("/get")
    public List<PostResponse> getAllPost(){
        return postService.getAllPost();
    }

    @PostMapping("/create")
    public PostResponse createPost(@RequestBody PostRequest request){
        return postService.createPost(request);
    }

    @PutMapping("/update/{id}")

    public PostResponse updatePost(@PathVariable Long id , @RequestBody PostRequest request){

        return postService.updatePost(id,request);

    }

@DeleteMapping("/delete/{id}")

    public String deletePost(@PathVariable Long id){

    return postService.deletePost(id);

}



}
