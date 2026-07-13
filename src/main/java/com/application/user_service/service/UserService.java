package com.application.user_service.service;

import com.application.user_service.dto.UserRequest;
import com.application.user_service.exceptionhandler.ResourceAlreadyExistsException;
import com.application.user_service.exceptionhandler.ResourceNotFoundException;
import com.application.user_service.models.User;
import com.application.user_service.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public Integer saveUserDetails( UserRequest userRequest) {
        User user=new User(userRequest.name(),userRequest.email(),userRequest.mobileNumber());
         if(userRepository.findByEmail(userRequest.email()).isPresent()){
             throw new ResourceAlreadyExistsException("User for given email already exists");
         }
             Optional<User> savedUser= Optional.of(userRepository.save(user));
             return savedUser.get().getId();


    }
    public void updateUserDetails( UserRequest userRequest) {
        Optional<User> user=userRepository.findByEmail(userRequest.email());
        if(user.isPresent()){
             userRepository.save(new User(userRequest.name(),userRequest.email(),userRequest.mobileNumber()));

                 }else{
            throw new ResourceNotFoundException("User with give email not found");
        }
        }


    public UserRequest getUserDetails(String email){
        Optional<User> user=userRepository.findByEmail(email);
        if(user.isPresent()){
            return new UserRequest(user.get().getName(),user.get().getEmail(),user.get().getMobileNumber());
        }else{
            throw new ResourceNotFoundException("User with give email not found");
        }

        }
    public void deleteUserDetails(String email){
        Optional<User> user=userRepository.findByEmail(email);
        if(user.isPresent()){
            userRepository.deleteByEmail(email);

        }else{
            throw new ResourceNotFoundException("User with give email not found");
        }

    }
    public ResponseEntity<List<UserRequest>> getAllUserDetails(){
       List<UserRequest> userList=userRepository.findAll().stream().map(u-> new UserRequest(u.getName(),u.getEmail(),u.getMobileNumber())).collect(Collectors.toList());
       if(userList.isEmpty()){
           throw new ResourceNotFoundException("Not Users are present");
       }
       return ResponseEntity.status(HttpStatus.OK).body(userList);

    }
}
