package com.example.common.exception;


import com.example.common.BaseResponse;
import com.example.common.BaseResponseStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /* ✅ BaseException을 처리하는 핸들러 */
    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<String> handleGlobalException(BaseException e) {
        return new BaseResponse<>(e.getStatus());
    }
}
