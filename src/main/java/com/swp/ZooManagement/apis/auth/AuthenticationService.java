package com.swp.ZooManagement.apis.auth;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.swp.ZooManagement.apis.accounts.Account;
import com.swp.ZooManagement.apis.accounts.AccountsRepository;
import com.swp.ZooManagement.apis.accounts.AccountsService;
import com.swp.ZooManagement.core.ErrorReport;
import com.swp.ZooManagement.errors.EntityNotFoundErrorReport;
import com.swp.ZooManagement.errors.LoginGoogleErrorReport;
import com.swp.ZooManagement.errors.ZooManagementException;
import com.swp.ZooManagement.security.JwtProvider;
import com.swp.ZooManagement.security.ZooManagementAuthentication;
import com.swp.ZooManagement.utils.enums.AccountStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Optional;

@Service
public class AuthenticationService {
    @Value("${swp.zoomanagement.gcp.client-id}")
    private String clientId;

    @Autowired
    private AccountsRepository accountsRepository;

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
            Optional<Account> findAccountResult = accountsRepository.findByEmail(email);
            if (findAccountResult.isEmpty()) {
                throw new ZooManagementException(new ErrorReport<>("Your account does not have permission to access the system.", null));
            }
            Account account = findAccountResult.get();
            if (account.getStatus() == AccountStatusEnum.INACTIVE) {
                throw new ZooManagementException(new ErrorReport<>("Your account is banned.", null));
            }
            return jwtProvider.signToken(account);
        } catch (ZooManagementException e) {
            throw e;
        } catch (Exception e) {
            throw new ZooManagementException(new LoginGoogleErrorReport(new HashMap<>() {{
                put("credential", credential);
                put("errorMessage", e.getMessage());
            }}));
        }
    }

    public Account getCurrentUser() {
        Account account = null;
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication auth = context.getAuthentication();
        try {
            account = (Account) auth.getPrincipal();
        } catch (Exception e) {

        }
        return account;
    }
}
