package com.swp.ZooManagement.apis.email;

import com.google.zxing.WriterException;
import com.swp.ZooManagement.errors.ZooManagementException;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("email")
public class EmailController {
    @Autowired
    EmailService emailService;
    
    @GetMapping("/{orderId}")
    public boolean sendQrCode(@PathVariable("orderId") String orderId) throws IOException, WriterException, MessagingException, ZooManagementException {
        emailService.sendHtmlMail(orderId);
        
        return true;
    }
}
