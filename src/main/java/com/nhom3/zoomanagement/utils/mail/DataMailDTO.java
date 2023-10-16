package com.nhom3.zoomanagement.utils.mail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataMailDTO {
    private String to;
    
    private String subject = "ZOO MANAGEMENT TICKET ORDER";
    
    private String template = "mail";
    
    private String content;
    
    private Map<String, Object> props;
}
