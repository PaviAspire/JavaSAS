package com.application.user_service.exceptionhandler;

public class ResourceAlreadyExistsException extends RuntimeException{

    private String message;
    public ResourceAlreadyExistsException(String message){
        super(message);
        this.message=message;
    }
}
