package com.nhom3.zoomanagement.google;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GoogleUserInfo {
    private String email;
    private boolean isEmailVerified;
    private String familyName;
    private String givenName;
    private String avatar;
}
