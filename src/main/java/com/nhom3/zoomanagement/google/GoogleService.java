package com.nhom3.zoomanagement.google;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.nhom3.zoomanagement.utils.GcpConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Service
public class GoogleService implements IGoogleService {
    @Autowired
    private GcpConfig gcpConfig;

    @Override
    public GoogleUserInfo fromCredential(String credential) throws GeneralSecurityException, IOException {
        GoogleUserInfo info = new GoogleUserInfo();
        HttpTransport transport = new NetHttpTransport();
        JsonFactory jsonFactory = new GsonFactory();
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                .setAudience(Collections.singletonList(gcpConfig.getClientId()))
                .build();

        GoogleIdToken idToken = verifier.verify(credential);

        if (idToken != null) {
            GoogleIdToken.Payload payload = idToken.getPayload();
            info.setEmail(payload.getEmail());
            info.setEmailVerified(payload.getEmailVerified());
            info.setFamilyName((String) payload.get("family_name"));
            info.setGivenName((String) payload.get("given_name"));
            info.setAvatar((String) payload.get("picture"));
        }
        return info;
    }
}
