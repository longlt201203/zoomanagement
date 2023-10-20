package com.nhom3.zoomanagement.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;

import javax.crypto.SecretKey;
import java.io.Serializable;
import java.security.*;
import java.security.interfaces.RSAKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Configuration
@Getter
@NoArgsConstructor
public class GcpConfig implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(GcpConfig.class);
    @Value("${nhom3.zoomanagement.gcp.client-id}")
    private String clientId;

    @Value("${nhom3.zoomanagement.gcp.client-secret}")
    private String clientSecret;

}
