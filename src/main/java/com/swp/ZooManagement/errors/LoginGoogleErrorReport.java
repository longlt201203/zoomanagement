package com.swp.ZooManagement.errors;

import com.swp.ZooManagement.core.ErrorReport;

import java.util.HashMap;

public class LoginGoogleErrorReport extends ErrorReport<HashMap<String, Object>> {
    public LoginGoogleErrorReport(HashMap<String, Object> data) {
        super("There is an error occurred when login with Google.", data);
    }
}
