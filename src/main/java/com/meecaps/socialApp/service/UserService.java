package com.meecaps.socialApp.service;

import com.meecaps.socialApp.entity.User;
import com.meecaps.socialApp.request.UserRequest;
import com.meecaps.socialApp.response.UserResponse;

import java.util.List;

public interface UserService  {

    String createUser(UserRequest userRequest);

    User updateUser(Long id, User userRequest);

    String deleteUser(Long id);

    List<UserResponse> getAllUsers();

    User getById(Long id);

    User findByUserEmail(String email);
}
