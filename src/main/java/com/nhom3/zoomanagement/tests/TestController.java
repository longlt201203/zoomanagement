package com.nhom3.zoomanagement.tests;

import com.nhom3.zoomanagement.errors.AppServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController implements ITestController {
    @Autowired
    private ITestService testService;

    @Override
    public List<TestDTO> getTests() {
        List<Test> tests = testService.getTests();
        return TestDTO.fromTestList(tests, true);
    }

    @Override
    public TestDTO createTest(CreateTestDTO dto) throws AppServiceException {
        Test test = testService.createTest(dto);
        return TestDTO.fromTest(test, true);
    }
}
