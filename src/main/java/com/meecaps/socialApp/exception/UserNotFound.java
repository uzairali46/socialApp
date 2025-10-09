package com.meecaps.socialApp.exception;

public class UserNotFound extends RuntimeException{

    public UserNotFound(String message){
        super(message);
    }
}
