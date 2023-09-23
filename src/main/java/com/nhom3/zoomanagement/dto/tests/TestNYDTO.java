package com.nhom3.zoomanagement.dto.tests;

import com.nhom3.zoomanagement.entities.TestNY;

import java.util.ArrayList;
import java.util.List;

public class TestNYDTO {
    public static TestNYDTO fromTestNY(TestNY testNY, boolean hasTest) {
        TestNYDTO dto = new TestNYDTO();
        dto.setName(testNY.getName());
        dto.setId(testNY.getId());
        if (hasTest) {
            dto.setTest(TestDTO.fromTest(testNY.getTest(), false));
        }
        return dto;
    }
    public static List<TestNYDTO> fromTestNYList(List<TestNY> testNYList, boolean hasTest) {
        List<TestNYDTO> dtoList = new ArrayList<>();
        for (TestNY testNY : testNYList) {
            dtoList.add(fromTestNY(testNY, hasTest));
        }
        return dtoList;
    }
    private int id;
    private String name;
    private TestDTO test;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TestDTO getTest() {
        return test;
    }

    public void setTest(TestDTO test) {
        this.test = test;
    }
}
