package com.nhom3.zoomanagement.google;

import java.io.IOException;
import java.security.GeneralSecurityException;

public interface IGoogleService {
    GoogleUserInfo fromCredential(String credential) throws GeneralSecurityException, IOException;
}
