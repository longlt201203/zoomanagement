package com.nhom3.zoomanagement.impl.tests;

import com.nhom3.zoomanagement.dto.tests.CreateTestDTO;
import com.nhom3.zoomanagement.entities.Test;
import com.nhom3.zoomanagement.errors.AppServiceException;
import com.nhom3.zoomanagement.errors.ErrorReport;
import com.nhom3.zoomanagement.repositories.ITestRepository;
import com.nhom3.zoomanagement.services.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService implements ITestService {
    @Autowired
    private ITestRepository testRepository;

    @Override
    public List<Test> getTests() {
        List<Test> tests = testRepository.findAll();
        return tests;
    }

    @Override
    public Test createTest(CreateTestDTO dto) throws AppServiceException {
        if (testRepository.existsByUniqueField(dto.getUniqueField())) {
            throw new AppServiceException(new ErrorReport("Unique field existed"));
        }
        Test test = new Test();
        test.setNullableField(dto.getNullableField());
        test.setTestNotNullField(dto.getTestNotNullField());
        test.setUniqueField(dto.getUniqueField());
        test = testRepository.save(test);
        return test;
    }
}
