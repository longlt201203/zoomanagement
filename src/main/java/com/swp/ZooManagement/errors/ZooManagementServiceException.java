/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp.ZooManagement.errors;

import com.swp.ZooManagement.core.ErrorReport;

/**
 *
 * @author Le Thanh Long
 */
public class ZooManagementServiceException extends ZooManagementException {
    
    public ZooManagementServiceException(String message) {
        super(new ErrorReport());
        ErrorReport report = getReport();
        report.setMessage(message);
        report.setTrace("com.swp.zoomanagement.ZooManagementService");
    }
    
}
