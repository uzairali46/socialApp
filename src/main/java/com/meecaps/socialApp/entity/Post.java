package com.meecaps.socialApp.entity;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String content;

    @DateTimeFormat
    @CreationTimestamp
    private String postedAt;

    @ManyToOne
    private User authorID;

    @OneToMany(mappedBy = "PostID", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(String postedAt) {
        this.postedAt = postedAt;
    }

    public User getAuthorID() {
        return authorID;
    }

    public void setAuthorID(User authorID) {
        this.authorID = authorID;
    }

    public void setAuthor(User user) {

    }
}
