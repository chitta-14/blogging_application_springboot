package com.blog.blogging_application.payload;

import com.blog.blogging_application.entity.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
        private Integer id;
        private String name;
        private String email;
        @JsonIgnore
        private String password;
        private String about;
        private Set<RoleDto> roles=new HashSet<>();

}
