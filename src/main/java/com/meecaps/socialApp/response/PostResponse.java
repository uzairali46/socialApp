package com.meecaps.socialApp.response;

import com.meecaps.socialApp.entity.Post;

public class PostResponse {


    private String content;
    private String postedAt;
    private String userName;

    public PostResponse(Post post){
        this.content = post.getContent();
        this.postedAt = post.getPostedAt();
        this.userName = post.getAuthorID().getUsername();
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
