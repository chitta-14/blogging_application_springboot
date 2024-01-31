package com.blog.blogging_application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;
    private String  categoryType;
    private String about;
    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    List<Post>posts=new ArrayList<>();

}
