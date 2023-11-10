package com.swp.ZooManagement.apis.email;

import com.google.zxing.WriterException;
import com.swp.ZooManagement.apis.orders.MyOrderResponseDto;
import com.swp.ZooManagement.apis.orders.MyOrdersService;
import com.swp.ZooManagement.errors.ZooManagementException;
import com.swp.ZooManagement.utils.QRCodeGenerator;
import jakarta.activation.DataHandler;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.util.ByteArrayDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;
    
    @Autowired
    private MyOrdersService myOrdersService;

    public void sendHtmlMail(String orderId) throws MessagingException, IOException, WriterException, ZooManagementException {
        MyOrderResponseDto order = myOrdersService.findById(orderId).toResponseDto();
        
        EmailDTO emailDTO = new EmailDTO();
        Map<String, Object> props = new HashMap<>();
        props.put("order", order);
        
        emailDTO.setProps(props);
        emailDTO.setTo(order.getEmail());
        
        MimeMessage message = mailSender.createMimeMessage();
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(emailDTO.getTo()));
        message.setSubject(emailDTO.getSubject());
        
//        create multi part
        MimeMultipart multipart = new MimeMultipart("related");

//        create html part
        MimeBodyPart htmlPart = new MimeBodyPart();
        Context context = new Context();
        context.setVariables(emailDTO.getProps());
        String html = templateEngine.process(emailDTO.getTemplate(), context);
        htmlPart.setContent(html, "text/html; charset=utf-8");

//        create image part
        MimeBodyPart imagePart = new MimeBodyPart();
        
        String cid = UUID.randomUUID().toString();
        byte[] image = QRCodeGenerator.getQRCodeImage(orderId, 250, 250);
        ByteArrayDataSource imageDataSource = new ByteArrayDataSource(image,"image/png");
        
        imagePart.setDataHandler(new DataHandler(imageDataSource));
        imagePart.setContentID("<" + cid + ">");
        imagePart.setDisposition(MimeBodyPart.INLINE);
        imagePart.setFileName("qr_code.png");

        multipart.addBodyPart(htmlPart);
        multipart.addBodyPart(imagePart);

        message.setContent(multipart);
        
        mailSender.send(message);
    }
}