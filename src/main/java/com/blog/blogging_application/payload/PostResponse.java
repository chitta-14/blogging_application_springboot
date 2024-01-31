package com.blog.blogging_application.payload;

import lombok.Data;

import java.util.List;

@Data
public class PostResponse {
    private List<PostDto> content;
    private Integer pageNumber;
    private Integer pageSize;
    private Integer totalNumberOfElementes;
    private Integer totalPages;
    private boolean lastPage;

}
