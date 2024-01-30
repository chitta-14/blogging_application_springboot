package com.blog.blogging_application.payload;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class CategoryDto {
    private Integer categoryId;
    private String  categoryType;
    private String about;
}
