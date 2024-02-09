package com.blog.blogging_application;

import com.blog.blogging_application.entity.Role;
import com.blog.blogging_application.payload.AppConstants;
import com.blog.blogging_application.repository.RoleRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class BloggingApplication implements CommandLineRunner {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepo roleRepo;

    public static void main(String[] args) {
        SpringApplication.run(BloggingApplication.class, args);
    }

    @Bean
    ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(passwordEncoder.encode("Chitta1234"));
        Role role=new Role();
        role.setId(AppConstants.ADMIN_USER);
        role.setName("ROLE_ADMIN");
        Role role1=new Role();
        role1.setId(AppConstants.NORMAL_USER);
        role1.setName("ROLE_NORMAL");
        List<Role> roleList= List.of(role,role1);
        System.out.println(this.roleRepo.saveAll(roleList));

    }
}
