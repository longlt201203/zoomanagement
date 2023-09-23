package com.nhom3.zoomanagement.tests;

import com.nhom3.zoomanagement.tests.CreateTestDTO;
import com.nhom3.zoomanagement.tests.TestDTO;
import com.nhom3.zoomanagement.errors.AppServiceException;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/test")
public interface ITestController {
    @GetMapping("/")
    List<TestDTO> getTests();

    @PostMapping("/")
    TestDTO createTest(@RequestBody @Valid CreateTestDTO dto) throws AppServiceException;
}
