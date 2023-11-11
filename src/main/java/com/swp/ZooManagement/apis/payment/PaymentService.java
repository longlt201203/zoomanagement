package com.swp.ZooManagement.apis.payment;

import com.swp.ZooManagement.apis.orders.MyOrder;
import com.swp.ZooManagement.apis.orders.MyOrdersService;
import com.swp.ZooManagement.errors.ZooManagementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PaymentService {
    @Autowired
    MyOrdersService myOrdersService;

    @Autowired
    PaymentConfig paymentConfig;

    public String createPaymentUrl(String orderId) throws ZooManagementException {
        MyOrder myOrder = myOrdersService.findById(orderId);

        Map<String, String> vnp_Params = new HashMap<>();
        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        System.out.println(myOrder.getTotal() * 100);
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);
        vnp_Params.put("vnp_Version", "2.1.0");
        vnp_Params.put("vnp_Command", "pay");
        vnp_Params.put("vnp_TmnCode", paymentConfig.getVnpTmnCode());
        vnp_Params.put("vnp_Amount", String.valueOf(new BigDecimal(myOrder.getTotal() * 100)));
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_OrderType", "other");
        vnp_Params.put("vnp_IpAddr", "127.0.0.1");
        vnp_Params.put("vnp_Locale", "vn");
        vnp_Params.put("vnp_OrderInfo", orderId);
        vnp_Params.put("vnp_ReturnUrl", paymentConfig.getVnpReturnUrl());
        vnp_Params.put("vnp_TxnRef", orderId);

        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);

        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();

        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = vnp_Params.get(fieldName);
            if ((fieldValue != null) && (!fieldValue.isEmpty())) {
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII));
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = PaymentConfig.hmacSHA512(paymentConfig.getVnpHashSecret(), hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = paymentConfig.getVnpPayUrl() + "?" + queryUrl;
        return paymentUrl;
    }

    public boolean isValidReturnUrl(String url) throws URISyntaxException {
        Map<String, String> fields = new LinkedHashMap<>();
        
        URI uri = new URI(url);
        String query = uri.getQuery();
        if (query == null) return false;
        
        String[] params = query.split("&");
        for (String param : params) {
            String[] keyValue = param.split("=");
            if (keyValue.length == 2) {
                String key = keyValue[0];
                String value = keyValue[1];
                fields.put(key, value);
            }
        }

        String vnp_SecureHash = fields.get("vnp_SecureHash");

        fields.remove("vnp_SecureHashType");
        fields.remove("vnp_SecureHash");

        StringBuilder queryBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : fields.entrySet()) {
            queryBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        queryBuilder.deleteCharAt(queryBuilder.length() - 1);

        String signValue = PaymentConfig.hmacSHA512(paymentConfig.getVnpHashSecret(), queryBuilder.toString());
        String vnp_ResponseCode = fields.get("vnp_ResponseCode");

        return "00".equals(vnp_ResponseCode) && vnp_SecureHash.equals(signValue);
    }
}
