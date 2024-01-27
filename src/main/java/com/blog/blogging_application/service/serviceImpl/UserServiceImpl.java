package com.blog.blogging_application.service.serviceImpl;
import com.blog.blogging_application.entity.User;
import com.blog.blogging_application.exception.ResourceNotFoundException;
import com.blog.blogging_application.repository.UserRepository;
import com.blog.blogging_application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Override
    public User createUser(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public User updateUser(User user, Integer userId) {
        User existingUser=this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","user_id",userId));
        existingUser.setName(user.getName());
        existingUser.setPassword(user.getPassword());
        existingUser.setEmail(user.getEmail());
        existingUser.setAbout(user.getAbout());
        User updatedUser=this.userRepository.save(existingUser);
        return updatedUser;
    }

    @Override
    public void deleteUser(Integer userId) {

    }

    @Override
    public List<User> getAllUser() {
        return null;
    }

    @Override
    public User getUserById(Integer userId) {
        User user=this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","userId",userId));
        return user;
    }
}
