package com.nhom3.zoomanagement.utils;

import com.nhom3.zoomanagement.errors.BadRequestException;
import com.nhom3.zoomanagement.errors.ErrorReport;
import com.nhom3.zoomanagement.errors.ValidationErrorReport;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.NotReadablePropertyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class AppControllerAdvisor {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorReport> handleBadRequest(BadRequestException e, HttpServletRequest req) {
        return new ResponseEntity<>(e.getReport(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorReport> handleValidationError(MethodArgumentNotValidException e, HttpServletRequest req) {
        List<String> errMsgs = new ArrayList<>();
        for (ObjectError error : e.getAllErrors()) {
            errMsgs.add(error.getDefaultMessage());
        }
        return handleBadRequest(new BadRequestException(new ValidationErrorReport(errMsgs)), req);
    }
}
