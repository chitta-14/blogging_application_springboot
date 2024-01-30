package com.blog.blogging_application.controller;

import com.blog.blogging_application.payload.ApiResponse;
import com.blog.blogging_application.payload.CategoryDto;
import com.blog.blogging_application.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //create
    @PostMapping("/")
    public ResponseEntity<CategoryDto>createCategory(@RequestBody CategoryDto categoryDto){
        return new ResponseEntity<CategoryDto>(this.categoryService.createCategory(categoryDto), HttpStatus.OK);
    }

    //update
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto>updateCategory(@RequestBody CategoryDto categoryDto,@PathVariable Integer categoryId){
        return new ResponseEntity<CategoryDto>(this.categoryService.updateCategory(categoryDto,categoryId),HttpStatus.OK);
    }

    //get single category
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto>getCategory(@PathVariable Integer categoryId){
        return new ResponseEntity<CategoryDto>(this.categoryService.getCategoryById(categoryId),HttpStatus.OK);
    }

    //get all category
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>>getAllCategory(){
        return new ResponseEntity<List<CategoryDto>>(this.categoryService.getAllCategory(),HttpStatus.OK);
    }

    //delete category
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse>deleteCategory(@PathVariable Integer categoryId){
        return new ResponseEntity<>(this.categoryService.deleteCategory(categoryId),HttpStatus.OK);
    }
}
