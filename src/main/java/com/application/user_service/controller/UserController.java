package com.application.user_service.controller;

import com.application.user_service.dto.UserRequest;
import com.application.user_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

private final UserService userService;

public UserController(UserService userService){
  this.userService=userService;
}

  @PostMapping(value = "/users",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createUser(@Valid @RequestBody UserRequest userRequest){
         Integer id=userService.saveUserDetails(userRequest);
         return ResponseEntity.status(HttpStatus.CREATED).body("UserId:"+id);

  }

    @GetMapping(value="/users",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserRequest> loadUserByEmail(@RequestParam String email){
       UserRequest userRequest=userService.getUserDetails(email);
        return ResponseEntity.status(HttpStatus.OK).body(userRequest);

    }

    @PutMapping(value="/users",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateUserDetails(@Valid @RequestBody UserRequest userRequest){
       userService.updateUserDetails(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("User Updated Successfully");

    }

    @GetMapping(value="/usersList",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserRequest>> findAllUserDetails(){

        return userService.getAllUserDetails();

    }
    @DeleteMapping(value="/users",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteUserByEmail(@RequestParam String email){
        userService.deleteUserDetails(email);
        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully");

    }
}
