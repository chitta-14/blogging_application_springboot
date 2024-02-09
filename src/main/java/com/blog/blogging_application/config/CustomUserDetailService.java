package com.blog.blogging_application.config;

import com.blog.blogging_application.entity.User;
import com.blog.blogging_application.exception.ResourceNotFoundException;
import com.blog.blogging_application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=this.userRepository.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("User","Email",0));
        return user;
    }
}
