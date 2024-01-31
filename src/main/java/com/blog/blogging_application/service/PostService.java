package com.blog.blogging_application.service;

import com.blog.blogging_application.entity.Post;
import com.blog.blogging_application.payload.ApiResponse;
import com.blog.blogging_application.payload.PostDto;
import com.blog.blogging_application.payload.PostResponse;

import java.util.List;

public interface PostService {
    public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
    public PostDto updatePost(PostDto postDto,Integer postId);
    public PostDto getPostById(Integer postId);
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);

    public ApiResponse deletePost(Integer postId);
    public List<PostDto>getPostByUser(Integer userId);
    public List<PostDto>getPostByCategory(Integer categoryId);
}
