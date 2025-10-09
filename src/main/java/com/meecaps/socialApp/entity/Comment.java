package com.meecaps.socialApp.entity;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Comment")

public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long ID;

    private String commentString;

    @ManyToOne
    private Post PostID;

    @ManyToOne
    private User author;


    @DateTimeFormat
    @CreationTimestamp
    private String commentedAt;

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getCommentString() {
        return commentString;
    }

    public void setCommentString(String commentString) {
        this.commentString = commentString;
    }

    public Post getPostID() {
        return PostID;
    }

    public void setPostID(Post postID) {
        PostID = postID;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getCommentedAt() {
        return commentedAt;
    }

    public void setCommentedAt(String commentedAt) {
        this.commentedAt = commentedAt;
    }
}
