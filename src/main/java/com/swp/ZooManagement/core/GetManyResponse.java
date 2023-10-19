/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp.ZooManagement.core;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Le Thanh Long
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetManyResponse<EntityType> {
    private int page;
    private int perPage;
    private int totalPage;
    private long totalRecord;
    private List<EntityType> data;
}
