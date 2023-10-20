package com.swp.ZooManagement.errors;

import com.swp.ZooManagement.core.ErrorReport;

import java.util.HashMap;

public class LoginGoogleErrorReport extends ErrorReport<HashMap<String, Object>> {
    public LoginGoogleErrorReport(HashMap<String, Object> data) {
        super("Login Google Error", data);
    }
}
