package com.swp.ZooManagement.apis.auth;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.swp.ZooManagement.apis.accounts.Account;
import com.swp.ZooManagement.apis.accounts.AccountsService;
import com.swp.ZooManagement.errors.LoginGoogleErrorReport;
import com.swp.ZooManagement.errors.ZooManagementException;
import com.swp.ZooManagement.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;

@Service
public class AuthenticationService {
    @Value("${swp.zoomanagement.gcp.client-id}")
    private String clientId;

    @Autowired
    private AccountsService accountsService;

    @Autowired
    private JwtProvider jwtProvider;

    public String loginWithGoogle(String credential) throws ZooManagementException {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
                .setAudience(Collections.singletonList(clientId))
                .build();

        try {
            GoogleIdToken idToken = verifier.verify(credential);
            GoogleIdToken.Payload payload = idToken.getPayload();
            String email = payload.getEmail();
            Account account = accountsService.findByEmail(email);
            return jwtProvider.signToken(account);
        } catch (Exception e) {
            throw new ZooManagementException(new LoginGoogleErrorReport(new HashMap<>() {{
                put("credential", credential);
                put("errorMessage", e.getMessage());
            }}));
        }
    }
}
