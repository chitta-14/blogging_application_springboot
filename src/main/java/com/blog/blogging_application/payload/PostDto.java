package com.blog.blogging_application.payload;

import com.blog.blogging_application.entity.Category;
import com.blog.blogging_application.entity.Comment;
import com.blog.blogging_application.entity.User;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class PostDto {
    private Integer postId;
    private String  title;
    private String  content;
    private Date addDate;
    private String image;
    private UserDto user;
    private CategoryDto category;

    private List<CommentDto> comments=new ArrayList<>();


}
