package com.blog.blogging_application.payload;

import com.blog.blogging_application.entity.Post;
import com.blog.blogging_application.entity.User;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class CommentDto {
    private Integer comment_id;
    private String comment;

}
