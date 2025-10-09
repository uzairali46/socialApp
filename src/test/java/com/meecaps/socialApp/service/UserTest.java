package com.meecaps.socialApp.service;

import com.meecaps.socialApp.entity.User;
import com.meecaps.socialApp.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class UserTest {
    @Autowired

    private UserRepository userRepository;


    @Test
    public void findById(){
        Optional<User> byId = userRepository.findById(1l);
        assertEquals("syeduzairali4699",byId.get().getUsername());

        assertEquals("uzair.007@example.com", byId.get().getEmail());

        assertNotNull(byId.get().getUsername());
    }

    @Test
    public void assertIterble(){

        List<String> target = List.of("a","b","c","d");
        List<String> actual = List.of("a","b","c","d");
        assertIterableEquals(target,actual);
    }

    @Test
    public void assertSameOrNot(){
        String obj1 = "uzair";
        String obj2 = obj1;
        String obj3 = new String(obj1);

        assertSame(obj1,obj3);
    }

    @Test
    public void arrayEquals(){
        int [] arr = {1,2,3,4};
        int [] arr1 = {1,2,3,4,5};

       assertArrayEquals(arr1,arr);
    }
}
