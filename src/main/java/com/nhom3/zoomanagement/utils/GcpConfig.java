package com.nhom3.zoomanagement.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@NoArgsConstructor
public class GcpConfig {
    @Value("${nhom3.zoomanagement.gcp.client-id}")
    private String clientId;

    @Value("${nhom3.zoomanagement.gcp.client-secret}")
    private String clientSecret;
}
