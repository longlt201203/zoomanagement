package com.nhom3.zoomanagement;

import com.nhom3.zoomanagement.utils.AuditingConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(AuditingConfiguration.class)
public class ZooManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZooManagementApplication.class, args);
    }

}
