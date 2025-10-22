package com.meecaps.socialApp.response;

import com.meecaps.socialApp.entity.User;

public class UserResponse {

    private String userName;
    private String email;

    public UserResponse(User user) {
        this.userName = user.getUserName();
                this.email = user.getEmail();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
