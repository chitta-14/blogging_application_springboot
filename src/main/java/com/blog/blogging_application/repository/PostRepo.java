package com.blog.blogging_application.repository;

import com.blog.blogging_application.entity.Category;
import com.blog.blogging_application.entity.Post;
import com.blog.blogging_application.entity.User;
import com.blog.blogging_application.payload.PostDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post,Integer> {
    List<Post>findByUser(User user);
    List<Post>findByCategory(Category category);
}
