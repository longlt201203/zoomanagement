package com.nhom3.zoomanagement.tests;

import com.nhom3.zoomanagement.errors.AppServiceException;

import java.util.List;

public interface ITestService {
    List<Test> getTests();
    Test createTest(CreateTestDTO dto) throws AppServiceException;
}
