package com.blog.blogging_application.controller;

import com.blog.blogging_application.payload.CommentDto;
import com.blog.blogging_application.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    //create
    @PostMapping("/user/{userId}/post/{postId}")
    public ResponseEntity<CommentDto>create(@PathVariable Integer userId, @PathVariable Integer postId, @RequestBody CommentDto commentDto){
        return new ResponseEntity<CommentDto>(this.commentService.createComment(commentDto,postId,userId), HttpStatus.CREATED);
    }
    //delete
    @DeleteMapping("/user/{userId}/comment/{commentId}")
    public ResponseEntity<String>deleteComment(@PathVariable Integer commentId,@PathVariable Integer userId){
        return new ResponseEntity<String>(this.commentService.delete(commentId,userId),HttpStatus.OK);
    }
    //get all comment
    @GetMapping("/")
    public ResponseEntity<List<CommentDto>>getAllComment(){
        return new ResponseEntity<List<CommentDto>>(this.commentService.getAllComment(),HttpStatus.OK);
    }
}
