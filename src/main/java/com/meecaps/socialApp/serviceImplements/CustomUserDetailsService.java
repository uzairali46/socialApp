package com.meecaps.socialApp.serviceImplements;

import com.meecaps.socialApp.entity.User;
import com.meecaps.socialApp.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;


public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;


    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username).
                orElseThrow(()-> new RuntimeException("User not found"));

      return new   org.springframework.security.core.userdetails.
                User(user.getUsername(),user.getPassword(), Collections.emptyList());
    }
}
