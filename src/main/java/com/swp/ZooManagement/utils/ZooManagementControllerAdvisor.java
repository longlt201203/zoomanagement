/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp.ZooManagement.utils;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.swp.ZooManagement.core.ErrorReport;
import com.swp.ZooManagement.errors.ValidationError;
import com.swp.ZooManagement.errors.ValidationErrorReport;
import com.swp.ZooManagement.errors.ZooManagementException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Le Thanh Long
 */
@ControllerAdvice
public class ZooManagementControllerAdvisor {
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorReport<?>> handleZooManagementException(Exception e, HttpServletRequest request) {
        e.printStackTrace();
        ErrorReport<?> report = new ErrorReport<>("Unhandled Exception", null);
        return new ResponseEntity<>(report, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ZooManagementException.class)
    protected ResponseEntity<ErrorReport<?>> handleZooManagementException(ZooManagementException e, HttpServletRequest request) {
        return new ResponseEntity<>(e.getReport(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorReport<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        List<ValidationError> errors = new ArrayList<>();
        for (FieldError error : e.getFieldErrors()) {
            errors.add(new ValidationError(error.getField(), error.getRejectedValue(), error.getDefaultMessage()));
        }
        return handleZooManagementException(new ZooManagementException(new ValidationErrorReport(errors)), request);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<ErrorReport<?>> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e, HttpServletRequest request) {
        List<ValidationError> errors = new ArrayList<>();
        errors.add(new ValidationError(e.getPropertyName(), e.getValue(), e.getMessage()));
        return handleZooManagementException(new ZooManagementException(new ValidationErrorReport(errors)), request);
    }
}
