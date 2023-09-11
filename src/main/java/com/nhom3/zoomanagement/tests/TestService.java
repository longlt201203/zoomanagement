package com.nhom3.zoomanagement.tests;

import com.nhom3.zoomanagement.errors.AppServiceException;
import com.nhom3.zoomanagement.errors.ErrorReport;
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
