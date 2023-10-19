/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp.ZooManagement.core;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Le Thanh Long
 */
@NoArgsConstructor
@Data
public class ErrorReport {
    private String trace;
    private String message;
}
