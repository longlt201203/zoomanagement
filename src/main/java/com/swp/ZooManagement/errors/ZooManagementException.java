/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp.ZooManagement.errors;

import com.swp.ZooManagement.core.ErrorReport;
import lombok.Getter;

/**
 *
 * @author Le Thanh Long
 */
@Getter
public class ZooManagementException extends Exception {
    private final ErrorReport report;
    
    public ZooManagementException(ErrorReport report) {
        super();
        this.report = report;
    }
}
