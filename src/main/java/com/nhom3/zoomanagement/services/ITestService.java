package com.nhom3.zoomanagement.services;

import com.nhom3.zoomanagement.dto.tests.CreateTestDTO;
import com.nhom3.zoomanagement.entities.Test;
import com.nhom3.zoomanagement.errors.AppServiceException;

import java.util.List;

public interface ITestService {
    List<Test> getTests();
    Test createTest(CreateTestDTO dto) throws AppServiceException;
}
