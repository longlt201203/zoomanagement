/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp.ZooManagement.utils;

import com.swp.ZooManagement.core.ErrorReport;
import com.swp.ZooManagement.errors.ZooManagementException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author Le Thanh Long
 */
@ControllerAdvice
public class ZooManagementControllerAdvisor {
    @ExceptionHandler(ZooManagementException.class)
    protected ResponseEntity<ErrorReport> handleZooManagementException(ZooManagementException e, HttpServletRequest request) {
        return new ResponseEntity<>(e.getReport(), HttpStatus.BAD_REQUEST);
    }
}
