package com.swp.ZooManagement.apis.payment;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@Service
@Getter
public class PaymentConfig {
    @Value("${payment.vnpPayUrl}")
    private String vnpPayUrl;

    @Value("${payment.vnpReturnUrl}")
    private String vnpReturnUrl;

    @Value("${payment.vnpTmnCode}")
    private String vnpTmnCode;

    @Value("${payment.vnpHashSecret}")
    private String vnpHashSecret;

    public static String hmacSHA512(final String key, final String data) {
        try {
            if (key == null || data == null) {
                throw new NullPointerException();
            }
            final Mac hmac512 = Mac.getInstance("HmacSHA512");
            byte[] hmackeyBytes = key.getBytes();
            final SecretKeySpec secretKey = new SecretKeySpec(hmackeyBytes, "HmacSHA512");
            hmac512.init(secretKey);
            byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
            byte[] result = hmac512.doFinal(dataBytes);
            StringBuilder sb = new StringBuilder(2 * result.length);
            for (byte b : result) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();
        } catch (Exception ex) {
            return "";
        }
    }
}
