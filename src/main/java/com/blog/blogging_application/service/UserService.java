package com.blog.blogging_application.service;

import com.blog.blogging_application.entity.User;
import com.blog.blogging_application.payload.ApiResponse;
import com.blog.blogging_application.payload.UserDto;

import java.util.List;

public interface UserService {
    public User createUser(User user);
    public UserDto updateUser(UserDto user,Integer userId);
    public ApiResponse deleteUser(Integer userId);
    public List<UserDto>getAllUser();
    public User getUserById(Integer userId);
    public UserDto registerUser(UserDto userDto);
}
