package com.meecaps.socialApp.serviceImplements;

import com.meecaps.socialApp.entity.Post;
import com.meecaps.socialApp.entity.User;
import com.meecaps.socialApp.repository.PostRepository;
import com.meecaps.socialApp.repository.UserRepository;
import com.meecaps.socialApp.request.PostRequest;
import com.meecaps.socialApp.response.PostResponse;
import com.meecaps.socialApp.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    final private PostRepository postRepository;
    final private UserRepository userRepository;


    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository){
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public List<PostResponse> getAllPost() {
        List<Post> postList = postRepository.findAll();

        // Stream

        return postList.stream().map(PostResponse::new).toList();

    }

    public PostResponse createPost(PostRequest postRequest) {

        User user = userRepository.findById(postRequest.getAuthorID())
                .orElseThrow( () -> new RuntimeException("User ID not Found"));

        Post post = new Post();
        post.setContent(postRequest.getContent());
        post.setAuthorID(user);
        Post save = postRepository.save(post);

        return new PostResponse(save);



    }

    public PostResponse updatePost(Long id, PostRequest request) {

        Post post = postRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("post not found")
        );

        post.setContent(request.getContent());
        Post save = postRepository.save(post);
        return new PostResponse(save);
    }

    public String deletePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new RuntimeException("post id not found"));
        postRepository.delete(post);
        return "Deleted Successfully";
    }
}
