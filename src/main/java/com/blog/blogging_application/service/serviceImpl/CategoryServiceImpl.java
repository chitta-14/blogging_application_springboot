package com.blog.blogging_application.service.serviceImpl;

import com.blog.blogging_application.entity.Category;
import com.blog.blogging_application.exception.ResourceNotFoundException;
import com.blog.blogging_application.payload.ApiResponse;
import com.blog.blogging_application.payload.CategoryDto;
import com.blog.blogging_application.repository.CategoryRepo;
import com.blog.blogging_application.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    ModelMapper modelMapper;

    //create
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category= this.categoryRepo.save(this.modelMapper.map(categoryDto, Category.class));
        return this.modelMapper.map(category, CategoryDto.class);
    }

   //update
    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category category= this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","categoryId",categoryId));
        category.setCategoryType(categoryDto.getCategoryType());
        category.setAbout(categoryDto.getAbout());
        Category newCategory=this.categoryRepo.save(category);
        return this.modelMapper.map(newCategory, CategoryDto.class);
    }
   //get single category
    @Override
    public CategoryDto getCategoryById(Integer categoryId) {
      return this.modelMapper.map(this.categoryRepo.findById(categoryId).
              orElseThrow(()->new ResourceNotFoundException("Category","categoryId",categoryId)), CategoryDto.class);
    }
   //get all categories
    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categoryList=this.categoryRepo.findAll();
        List<CategoryDto>categoryDtos = categoryList.stream().map((category)->this.modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
        return categoryDtos;
    }
    //delete category
    @Override
    public ApiResponse deleteCategory(Integer categoryId) {
        Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","categoryId",categoryId));
        this.categoryRepo.delete(category);
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setMessage("category sucessfully deleted");
        apiResponse.setResult(true);
        return apiResponse;
    }
}
