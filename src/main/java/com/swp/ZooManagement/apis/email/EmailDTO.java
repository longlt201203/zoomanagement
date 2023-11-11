package com.swp.ZooManagement.apis.email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailDTO {
    private String to;

    private String subject = "ZOO MANAGEMENT TICKET ORDER";

    private String template = "email";

    private String content;

    private Map<String, Object> props;
}
