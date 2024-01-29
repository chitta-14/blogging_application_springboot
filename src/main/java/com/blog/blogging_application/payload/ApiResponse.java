package com.blog.blogging_application.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApiResponse {
    private String message;
    private boolean result;

    public ApiResponse(String message, boolean result) {
        this.message = message;
        this.result = result;
    }
}
