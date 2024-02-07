package com.blog.blogging_application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;
    private String  title;
    private String  content;
    private Date addDate;
    private String image;
    @ManyToOne
    private User user;
    @ManyToOne
    private Category category;
    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    private List<Comment>comments=new ArrayList<>();


}
