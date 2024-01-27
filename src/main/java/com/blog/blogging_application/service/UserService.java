package com.blog.blogging_application.service;

import com.blog.blogging_application.entity.User;

import java.util.List;

public interface UserService {
    public User createUser(User user);
    public User updateUser(User user,Integer userId);
    public void deleteUser(Integer userId);
    public List<User>getAllUser();
    public User getUserById(Integer userId);
}
