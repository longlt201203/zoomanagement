/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp.ZooManagement.errors;

/**
 *
 * @author Le Thanh Long
 */
public class EntityNotFoundException extends ZooManagementServiceException {
    
    public EntityNotFoundException() {
        super("Entity not found");
    }
    
}
