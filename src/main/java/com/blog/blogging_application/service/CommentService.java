package com.blog.blogging_application.service;

import com.blog.blogging_application.payload.CommentDto;

import java.util.List;

public interface CommentService {
    public CommentDto createComment(CommentDto commentDto,Integer postId,Integer userId);
    public String delete(Integer commentId,Integer userId);
    public List<CommentDto> getAllComment();

}
