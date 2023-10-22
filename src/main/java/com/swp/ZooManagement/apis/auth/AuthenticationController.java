package com.swp.ZooManagement.apis.auth;

import com.swp.ZooManagement.errors.ZooManagementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("login-google")
    public LoginResponse loginGoogle(@RequestParam String credential) throws GeneralSecurityException, IOException, ZooManagementException {
        LoginResponse response = new LoginResponse();
        response.setAccessToken(authenticationService.loginWithGoogle(credential));
        return response;
    }
}
