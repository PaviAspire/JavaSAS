package com.application.user_service.controller;

import com.application.user_service.dto.UserRequest;
import com.application.user_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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
    public ResponseEntity<EntityModel<UserRequest>> createUser(@Valid @RequestBody UserRequest userRequest){
      UserRequest userRequest1=userService.saveUserDetails(userRequest);
         EntityModel<UserRequest> entityModel=EntityModel.of(userRequest1);
      entityModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).createUser(userRequest)).withSelfRel()).add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).findAllUserDetails()).withRel("AllUsers"));

      entityModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).loadUserByEmail(userRequest1.email())).withRel("GetUserByEmail"));

      return ResponseEntity.status(HttpStatus.CREATED).body(entityModel);

  }

    @GetMapping(value="/users",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<UserRequest>> loadUserByEmail(@RequestParam String email){
       UserRequest userRequest=userService.getUserDetails(email);
        EntityModel<UserRequest> entityModel=EntityModel.of(userRequest);
        entityModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).loadUserByEmail(userRequest.email())).withSelfRel()).add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).findAllUserDetails()).withRel("AllUsers"));

        return ResponseEntity.status(HttpStatus.OK).body(entityModel);

    }

    @PutMapping(value="/users",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateUserDetails(@Valid @RequestBody UserRequest userRequest){
       userService.updateUserDetails(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("User Updated Successfully");

    }

    @GetMapping(value="/usersList",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<UserRequest>>> findAllUserDetails(){

        List<UserRequest> userRequests=userService.getAllUserDetails();
        List<EntityModel<UserRequest>> entityModels=userRequests.stream()
                                     .map(EntityModel::of).toList();
        CollectionModel<EntityModel<UserRequest>> collectionModel=CollectionModel.of(entityModels);
        collectionModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).deleteUserByEmail("email")).withRel("DeleteUser"));
        return ResponseEntity.status(HttpStatus.OK).body(collectionModel);

    }
    @DeleteMapping(value="/users",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteUserByEmail(@RequestParam String email){
        userService.deleteUserDetails(email);
        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully");

    }
}
