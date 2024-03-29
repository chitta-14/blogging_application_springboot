package com.blog.blogging_application.service.serviceImpl;

import com.blog.blogging_application.entity.Category;
import com.blog.blogging_application.entity.Post;
import com.blog.blogging_application.entity.User;
import com.blog.blogging_application.exception.ResourceNotFoundException;
import com.blog.blogging_application.payload.ApiResponse;
import com.blog.blogging_application.payload.PostDto;
import com.blog.blogging_application.payload.PostResponse;
import com.blog.blogging_application.repository.CategoryRepo;
import com.blog.blogging_application.repository.PostRepo;
import com.blog.blogging_application.repository.UserRepository;
import com.blog.blogging_application.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepo categoryRepo;
    @Override
    public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
        User user=this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","userId",userId));
        Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","CategoryId",categoryId));
        Post post=this.modelMapper.map(postDto, Post.class);
        post.setImage("default.png");
        post.setUser(user);
        post.setCategory(category);
        post.setAddDate(new Date());
        Post addedPost=this.postRepo.save(post);
        return this.modelMapper.map(addedPost,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post existingPost=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","PostId",postId));
        existingPost.setTitle(postDto.getTitle());
        existingPost.setContent(postDto.getContent());
        existingPost.setAddDate(new Date());
        existingPost.setImage("default.png");

        Post updatedPost=this.postRepo.save(existingPost);
        return this.modelMapper.map(updatedPost, PostDto.class) ;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post existingPost=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","PostId",postId));
        return this.modelMapper.map(existingPost,PostDto.class);
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {
        Sort sort=(sortDir.equalsIgnoreCase("asc"))?(Sort.by(sortBy).ascending()):(Sort.by(sortBy).descending());
        PageRequest p= PageRequest.of(pageNumber,pageSize,sort);
        Page<Post> pagePosts=this.postRepo.findAll(p);
        List<Post> posts=pagePosts.getContent();
        List<PostDto> postDtos =posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());

        PostResponse postResponse=new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePosts.getNumber());
        postResponse.setPageSize(pagePosts.getSize());
        postResponse.setLastPage(pagePosts.isLast());
        postResponse.setTotalPages(pagePosts.getTotalPages());
        postResponse.setTotalNumberOfElementes(pagePosts.getNumberOfElements());
        return postResponse;
    }

    @Override
    public ApiResponse deletePost(Integer postId) {
        Post existingPost=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","PostId",postId));
        this.postRepo.delete(existingPost);
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setMessage("post deleted sucessfully...");
        apiResponse.setResult(true);
        return apiResponse;
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {
        User user=this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","userId",userId));
        List<Post>postList=this.postRepo.findByUser(user);
        List<PostDto>postDtos=postList.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {
        Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","categoryId",categoryId));
        List<Post>postList=this.postRepo.findByCategory(category);
        List<PostDto>postDtos=postList.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        System.out.println(postDtos);
        return postDtos;
    }

    @Override
    public List<PostDto> findByTitleContaining(String keyword) {
        List<Post> posts=this.postRepo.findByTitleContaining(keyword);
        List<PostDto>postDtos=posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }
}
