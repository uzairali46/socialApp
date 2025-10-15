package com.meecaps.socialApp.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    @Column(nullable = false)
    private Long Id;
    private String username;
    private String password;

    @Column(nullable = false)
    private String email;

}
