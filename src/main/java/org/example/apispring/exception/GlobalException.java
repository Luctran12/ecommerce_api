package org.example.apispring.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.example.apispring.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse> handlingRuntimeException(Exception e, HttpServletRequest request) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(e.getMessage());

        return ResponseEntity.badRequest().body(apiResponse);
    }


}
