package com.nhom3.zoomanagement.google;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.nhom3.zoomanagement.utils.GcpConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Controller
public class GoogleController {
    @Autowired
    private IGoogleService googleService;

    @GetMapping("/google")
    protected String testGooglePage() {
        return "google.html";
    }

    @PostMapping("/test-login-google")
    @ResponseBody
    protected GoogleUserInfo testLoginGoogle(@RequestBody Map<String, String> params) throws GeneralSecurityException, IOException {
        GoogleUserInfo info = googleService.fromCredential(params.get("credential"));
        return info;
    }
}
