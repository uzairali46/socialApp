package com.meecaps.socialApp.serviceImplements;


import com.meecaps.socialApp.entity.User;
import com.meecaps.socialApp.exception.UserNotFound;
import com.meecaps.socialApp.repository.UserRepository;
import com.meecaps.socialApp.request.UserRequest;
import com.meecaps.socialApp.response.UserResponse;
import com.meecaps.socialApp.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    final private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public String createUser(UserRequest userRequest) {

        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
       userRepository.save(user);
         return "user created successfully";

        // return ResponseEntity.ok("User created successfully");

//        return ResponseEntity.status(HttpStatus.CREATED).body(
//                Map.of(
//                        "message", "User Created successfully",
//                        "body", save,
//                        "success", true
//
//                )
//        );


    }

    public List<User> getAllUser() {
      return  userRepository.findAll();
    }

    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new  RuntimeException("id not found"));
    }


    public User updateUser(Long id, User userRequest) {

        User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("id not found"));
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());
        user.setEmail(userRequest.getEmail());
         return   userRepository.save(user);

    }

    public String deleteUser(Long id) {
        userRepository.deleteById(id);
        return "Deleted Successfully";
    }

    public User findByUserEmail(String email){

        User user = userRepository.findByEmail(email)
                .orElseThrow( () -> new UserNotFound("User not found by " + email+ " email"));
        return user;
    }

//    public List<UserResponse> getAllUsers(){
//        List<User> userList = userRepository.findAll();
//        return userList.stream().map(UserResponse :: new).toList();

    public List<UserResponse> getAllUsers(){
        List<User> userList = userRepository.findAll();
        List<UserResponse> user = new ArrayList();
        for(User us : userList){
            UserResponse  userResponse  = new UserResponse(us);
        user.add(userResponse);

        }
        return user;
    }
}
