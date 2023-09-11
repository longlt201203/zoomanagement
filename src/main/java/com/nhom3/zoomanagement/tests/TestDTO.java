package com.nhom3.zoomanagement.tests;

import java.util.ArrayList;
import java.util.List;

public class TestDTO {
    public static TestDTO fromTest(Test test) {
        TestDTO testDTO = new TestDTO();
        testDTO.setId(test.getId());
        testDTO.setNullableField(test.getNullableField());
        testDTO.setTestNotNullField(test.getTestNotNullField());
        testDTO.setUniqueField(test.getUniqueField());
        return testDTO;
    }

    public static List<TestDTO> fromTestList(List<Test> testList) {
        List<TestDTO> testDTOList = new ArrayList<>();
        for (Test test : testList) {
            TestDTO testDTO = fromTest(test);
            testDTOList.add(testDTO);
        }
        return testDTOList;
    }

    private int id;
    private String testNotNullField;
    private String nullableField;
    private String uniqueField;

    public String getUniqueField() {
        return uniqueField;
    }

    public void setUniqueField(String uniqueField) {
        this.uniqueField = uniqueField;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTestNotNullField() {
        return testNotNullField;
    }

    public void setTestNotNullField(String testNotNullField) {
        this.testNotNullField = testNotNullField;
    }

    public String getNullableField() {
        return nullableField;
    }

    public void setNullableField(String nullableField) {
        this.nullableField = nullableField;
    }
}
