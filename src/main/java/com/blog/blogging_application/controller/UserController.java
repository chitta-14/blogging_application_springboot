package com.blog.blogging_application.controller;

import com.blog.blogging_application.entity.User;
import com.blog.blogging_application.payload.ApiResponse;
import com.blog.blogging_application.payload.UserDto;
import com.blog.blogging_application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private UserService userService;
    //create user
    @PostMapping("/")
    public ResponseEntity<User>createUser(@RequestBody User user){
        return new ResponseEntity<User>(this.userService.createUser(user), HttpStatus.CREATED);
    }
    //getUserById
    @GetMapping("/{userId}")
    public ResponseEntity<User>getUserById(@PathVariable("userId")Integer userId){
        return new ResponseEntity<User>(this.userService.getUserById(userId),HttpStatus.OK );
    }
    //get all user
    @GetMapping("/")
    public ResponseEntity<List<UserDto>>getAllUser(){
        return new ResponseEntity<List<UserDto>>(this.userService.getAllUser(),HttpStatus.OK);
    }
    //update user
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto>updateUser(@PathVariable("userId")Integer userId,@RequestBody UserDto userDto){
        return  new ResponseEntity<UserDto>(this.userService.updateUser(userDto,userId),HttpStatus.OK);
    }
    //delete user
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse>deleteUser(@PathVariable("userId")Integer userId){
        return new ResponseEntity<ApiResponse>(this.userService.deleteUser(userId),HttpStatus.OK);
    }
}
