package com.swp.ZooManagement.apis.payment;

import com.swp.ZooManagement.errors.ZooManagementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.Map;

@RestController()
@RequestMapping("payment")
public class PaymentController {
    @Autowired
    PaymentConfig paymentConfig;
    
    @Autowired
    PaymentService paymentService;
    
    @GetMapping("/")
    public String createPaymentUrl(@RequestParam("orderId") String orderId) throws UnsupportedEncodingException, ZooManagementException {
        return paymentService.createPaymentUrl(orderId);
    }
    
    @PostMapping("is-valid-return-url")
    public boolean isValidReturnUrl(@RequestBody() Map<String, String> body) throws URISyntaxException {
        String url = body.get("url");
        return paymentService.isValidReturnUrl(url);
    }
}
