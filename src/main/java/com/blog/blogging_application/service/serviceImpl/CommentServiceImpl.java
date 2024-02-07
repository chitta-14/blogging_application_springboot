package com.blog.blogging_application.service.serviceImpl;

import com.blog.blogging_application.entity.Comment;
import com.blog.blogging_application.entity.Post;
import com.blog.blogging_application.entity.User;
import com.blog.blogging_application.exception.ResourceNotFoundException;
import com.blog.blogging_application.payload.CommentDto;
import com.blog.blogging_application.repository.CommentRepo;
import com.blog.blogging_application.repository.PostRepo;
import com.blog.blogging_application.repository.UserRepository;
import com.blog.blogging_application.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private CommentRepo commentRepo;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId, Integer userId) {
        User user=this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","userId",userId));
        Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postId",postId));
        Comment comment=this.modelMapper.map(commentDto,Comment.class);
        comment.setUser(user);
        comment.setPost(post);
        Comment newComment=this.commentRepo.save(comment);
        return this.modelMapper.map(newComment,CommentDto.class);
    }

    @Override
    public String delete(Integer commentId, Integer userId) {
        Comment comment=this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","commentId",commentId));
        User user=this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","userId",userId));
        this.commentRepo.delete(comment);
        return "Sucessfully deleted ...";
    }

    @Override
    public List<CommentDto> getAllComment() {
        List<Comment>comments=this.commentRepo.findAll();
        List<CommentDto>commentDtos=comments.stream().map((comment)->this.modelMapper.map(comment,CommentDto.class)).collect(Collectors.toList());
        return commentDtos;
    }
}
