package com.nhom3.zoomanagement.google;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.nhom3.zoomanagement.accounts.Account;
import com.nhom3.zoomanagement.accounts.AccountsRepository;
import com.nhom3.zoomanagement.utils.GcpConfig;
import com.nhom3.zoomanagement.utils.jwt.JwtProvider;
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
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders="*")
public class GoogleController {
    @Autowired
    private IGoogleService googleService;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired

    AccountsRepository accountsRepository;
    @GetMapping("/google")
    protected String testGooglePage() {
        return "google.html";
    }
//    @CrossOrigin(maxAge = 3600, allowCredentials = "true")

    @CrossOrigin(origins = "http://localhost:3000", allowedHeaders = {"Requestor-Type", "Authorization"}, exposedHeaders = "X-Get-Header")
    @PostMapping("/test-login-google")
    @ResponseBody
    protected String testLoginGoogle(@RequestBody Map<String, String> params) throws GeneralSecurityException, IOException {
        System.out.println("credential: "+ params.get("credential"));
        GoogleUserInfo info = googleService.fromCredential(params.get("credential"));
        Account account = accountsRepository.findByEmail(info.getEmail());
        String jwt = jwtProvider.generateJwtToken(account.getEmail(), account.getRole().toString());
        System.out.println("new Jwt "+ jwt);

        return jwt;
    }
}
