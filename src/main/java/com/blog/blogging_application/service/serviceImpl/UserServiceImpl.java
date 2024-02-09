package com.blog.blogging_application.service.serviceImpl;
import com.blog.blogging_application.entity.Role;
import com.blog.blogging_application.entity.User;
import com.blog.blogging_application.exception.ResourceNotFoundException;
import com.blog.blogging_application.payload.ApiResponse;
import com.blog.blogging_application.payload.AppConstants;
import com.blog.blogging_application.payload.UserDto;
import com.blog.blogging_application.repository.RoleRepo;
import com.blog.blogging_application.repository.UserRepository;
import com.blog.blogging_application.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepo roleRepo;
    @Override
    public User createUser(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public UserDto updateUser(UserDto user, Integer userId) {
        User existingUser=this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","user_id",userId));
        existingUser.setName(user.getName());
        existingUser.setPassword(user.getPassword());
        existingUser.setEmail(user.getEmail());
        existingUser.setAbout(user.getAbout());
        User updatedUser=this.userRepository.save(existingUser);
        return this.modelMapper.map(updatedUser,UserDto.class);
    }

    @Override
    public ApiResponse deleteUser(Integer userId) {
        User user =this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","userId",userId));
        this.userRepository.delete(user);
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setMessage("user deleted sucessfully");
        apiResponse.setResult(true);
        return apiResponse;

    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> users=this.userRepository.findAll();
        List<UserDto> userDtos=users.stream().map((user)->this.modelMapper.map(user,UserDto.class)).collect(Collectors.toList());
        return userDtos;
    }


    @Override
    public User getUserById(Integer userId) {
        User user=this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","userId",userId));
        return user;
    }

    @Override
    public UserDto registerUser(UserDto userDto) {
        User user=this.modelMapper.map(userDto,User.class);
        user.setPassword(this.passwordEncoder.encode(userDto.getPassword()));
        Role role=this.roleRepo.findById(AppConstants.ADMIN_USER).get();
        user.getRoles().add(role);
        User newUser=this.userRepository.save(user);
        return this.modelMapper.map(newUser,UserDto.class);
    }
}
