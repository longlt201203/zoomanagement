package com.swp.ZooManagement.utils;

import com.swp.ZooManagement.errors.ZooManagementException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UtilsController {
    @RequestMapping("/login-page")
    private String loginPage() {
        return "google.html";
    }

    @GetMapping("/enums")
    @ResponseBody
    private Map<String, List<Object>> getEnums(@RequestParam(name = "enums") String enumsStr) throws ZooManagementException {
        String[] enumNames = enumsStr.split(",");
        Map<String, List<Object>> map = new HashMap<>();
        for (String enumName : enumNames) {
            String className = "com.swp.ZooManagement.utils.enums." + enumName;
            try {
                Class<?> enumClass = Class.forName(className);
                map.put(enumName, List.of(enumClass.getEnumConstants()));
            } catch (ClassNotFoundException e) {
                System.out.println(className);
            }
        }
        return map;
    }
}
