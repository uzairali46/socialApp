package com.meecaps.socialApp.serviceImplements;


import com.meecaps.socialApp.entity.User;
import com.meecaps.socialApp.exception.UserNotFound;
import com.meecaps.socialApp.repository.UserRepository;
import com.meecaps.socialApp.request.UserRequest;
import com.meecaps.socialApp.response.UserResponse;
import com.meecaps.socialApp.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    final private UserRepository userRepository;

    final private EntityManager entityManager ;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, EntityManager entityManager, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.entityManager = entityManager;
        this.passwordEncoder = passwordEncoder;
    }


    public String createUser(UserRequest userRequest) {

        User user = new User();
        user.setUserName(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setRole(userRequest.getRole());
//        user.setPassword(userRequest.getPassword());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
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
        user.setUserName(userRequest.getUserName());
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

    public List<UserResponse> getAllUsers() {

//        List<User> userList = userRepository.findAll();
//        List<UserResponse> user = new ArrayList();
//        for(User us : userList){
//            UserResponse  userResponse  = new UserResponse(us);
//        user.add(userResponse);
//
//        }
//        return user;
//         }

        List<User> userList = userRepository.findAll();
        return userList.stream().map(UserResponse :: new).toList();

    }






    public List<User> getAllUserByCriteria(){


    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

    CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

    Root<User> root  = criteriaQuery.from(User.class);

    // select * from user

    criteriaQuery.select(root);

    return entityManager .createQuery(criteriaQuery).getResultList();



    }


    public List<User> GetUserNameByCriteriaApI(String userName){

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

        Root <User> root = criteriaQuery.from(User.class);

        Predicate condition = criteriaBuilder.equal(root.get("username"), userName);

        criteriaQuery.select(root).where(condition);

        TypedQuery<User> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }




}