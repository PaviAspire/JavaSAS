package com.application.user_service.exceptionhandler;

public class ResourceNotFoundException extends RuntimeException{

     private String message;
    public ResourceNotFoundException(String message){
        super(message);
        this.message=message;
    }
}
