package com.blog.blogging_application.service;

import com.blog.blogging_application.payload.ApiResponse;
import com.blog.blogging_application.payload.CategoryDto;

import java.util.List;

public interface CategoryService {
    public CategoryDto createCategory(CategoryDto categoryDto);
    public CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);
    public CategoryDto getCategoryById(Integer categoryId);
    public List<CategoryDto>getAllCategory();
    public ApiResponse deleteCategory(Integer categoryId);
}
