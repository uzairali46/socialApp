package com.meecaps.socialApp.serviceMockito;

import com.meecaps.socialApp.entity.User;
import com.meecaps.socialApp.repository.UserRepository;
import com.meecaps.socialApp.serviceImplements.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class ServiceMockitoTest {

//    BY USING J-UNIT
//    @Autowired
//    private UserRepository userRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
        public void testFIndByEmail(){

        String email = "md@gmail.com";

        User userMock = new User();
        userMock.setId(1L);
        userMock.setUsername("uzair");
        userMock.setEmail(email);
        userMock.setPassword("abcdfg");

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(userMock));
        User byUserEmail = userService.findByUserEmail(email);
        assertNotNull(byUserEmail);
        assertEquals("uzair",byUserEmail.getUsername());
        assertEquals("abcdfg", byUserEmail.getPassword());



    }

}
