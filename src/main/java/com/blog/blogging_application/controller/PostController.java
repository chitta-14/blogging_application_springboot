package com.blog.blogging_application.controller;

import com.blog.blogging_application.payload.ApiResponse;
import com.blog.blogging_application.payload.AppConstants;
import com.blog.blogging_application.payload.PostDto;
import com.blog.blogging_application.payload.PostResponse;
import com.blog.blogging_application.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/post")
public class PostController {
    @Autowired
    private PostService postService;
    //create user
    @PostMapping("/user/{userId}/category/{categoryId}")
    public ResponseEntity<PostDto> createUser(@RequestBody PostDto postDto,@PathVariable Integer userId,@PathVariable Integer categoryId){
        return new ResponseEntity<PostDto>(this.postService.createPost(postDto,userId,categoryId), HttpStatus.CREATED);
    }
    //getUserById
    @GetMapping("/{postId}")
    public ResponseEntity<PostDto>getPostById(@PathVariable("postId")Integer postId){
        return new ResponseEntity<PostDto>(this.postService.getPostById(postId),HttpStatus.OK );
    }
    //get all user
    @GetMapping("/")
    public ResponseEntity<PostResponse>getAllPost(@RequestParam(value="pageNumber",defaultValue = "0",required = false)Integer pageNumber,
                                                   @RequestParam(value = "pageSize",defaultValue = AppConstants.PAGESIZE,required = false)Integer pageSize,
                                                  @RequestParam(value = "sortBy",defaultValue = AppConstants.SORTBY,required = false)String sortBy,
                                                  @RequestParam(value = "sortDir",defaultValue = AppConstants.SORTDIR,required = false)String sortDir){
        return new ResponseEntity<PostResponse>(this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir),HttpStatus.OK);
    }
    //update user
    @PutMapping("/{postId}")
    public ResponseEntity<PostDto>updatePost(@PathVariable("postId")Integer postId,@RequestBody PostDto postDto){
        return  new ResponseEntity<PostDto>(this.postService.updatePost(postDto,postId),HttpStatus.OK);
    }
    //delete user
    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResponse>deletePost(@PathVariable("postId")Integer postId){
        return new ResponseEntity<ApiResponse>(this.postService.deletePost(postId),HttpStatus.OK);
    }
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<PostDto>>getPostByCategory(@PathVariable Integer categoryId){
        return new ResponseEntity<List<PostDto>>(this.postService.getPostByCategory(categoryId),HttpStatus.OK );
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostDto>>getPostByUser(@PathVariable Integer userId){
        return new ResponseEntity<List<PostDto>>(this.postService.getPostByUser(userId),HttpStatus.OK );
    }
}
