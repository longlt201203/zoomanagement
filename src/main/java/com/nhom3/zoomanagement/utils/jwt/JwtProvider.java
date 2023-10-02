package com.nhom3.zoomanagement.utils.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${jwt.expire.time}")
    private long tokenExpireTime ;

    @Value("${jwt.secret}")
    private String secret;
}
