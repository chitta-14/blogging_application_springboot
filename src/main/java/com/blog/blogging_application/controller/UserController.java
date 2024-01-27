package com.blog.blogging_application.controller;

import com.blog.blogging_application.entity.User;
import com.blog.blogging_application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/")
    public ResponseEntity<User>createUser(@RequestBody User user){
        return new ResponseEntity<User>(this.userService.createUser(user), HttpStatus.CREATED);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<User>getUserById(@PathVariable("userId")Integer userId){
        return new ResponseEntity<User>(this.userService.getUserById(userId),HttpStatus.OK );
    }
}
