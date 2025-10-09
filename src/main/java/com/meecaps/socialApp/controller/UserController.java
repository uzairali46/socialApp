package com.meecaps.socialApp.controller;

import  com.meecaps.socialApp.entity.User;
import com.meecaps.socialApp.request.UserRequest;
import com.meecaps.socialApp.response.UserResponse;
import com.meecaps.socialApp.service.UserService;
import com.meecaps.socialApp.serviceImplements.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// Lombok library
//@Getter / @Setter
//@NoArgsConstructor, @AllArgsConstructor,
// @RequiredArgsConstructor(final, @NonNull)

//@Data
//A shortcut for:
//@Getter, @Setter, @ToString, @EqualsAndHashCode, and
// @RequiredArgsConstructor.

//@Builder
//Creates a Builder Pattern implementation for object creation.



@RestController
@RequestMapping("/User")
public class UserController {

    final private UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")

    public String createUser(@RequestBody UserRequest userrequest){
        return userService.createUser(userrequest);
    }

    @GetMapping("/get")
    public List<UserResponse> getAllUser(){
        return userService.getAllUsers();
    }

    @GetMapping("/by-email")
    public User findByUserEmail(@RequestParam String email){
        return userService.findByUserEmail(email);
    }

    @GetMapping("/get/{id}")
    public User getById(@PathVariable Long id){
        return userService.getById(id);
    }

    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User userRequest){
        return userService.updateUser(id, userRequest);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id){
        return userService.deleteUser(id);
    }


}
