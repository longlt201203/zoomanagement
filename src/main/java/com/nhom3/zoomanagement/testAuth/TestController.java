//package com.nhom3.zoomanagement.testAuth;
//
//import com.nhom3.zoomanagement.accounts.AccountDTO;
//import com.nhom3.zoomanagement.utils.jwt.JwtProvider;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@CrossOrigin(origins = "*", maxAge = 3600)
//@RestController
//@RequestMapping("/api/auth")
//public class TestController {
////    @Autowired
////    AuthenticationManager authenticationManager;
////    @Autowired
////    JwtProvider jwtUtils;
////    @Autowired
////    private PasswordEncoder passwordEncoder;
//    @GetMapping("/login")
//    public String viewLoginPage() {
//        return "login";
//    }
//
//    @GetMapping("/")
//    public String hello() {
//        return "home";
//    }
//
//    
//    @PostMapping("/signin")
//    public AccountDTO authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
//
////        String encodedPassword = passwordEncoder.encode(loginRequest.getId());
////        System.out.println(encodedPassword);
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getId()));
//        System.out.println("sigin auth: " + authentication.getAuthorities());
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String jwt = jwtUtils.generateJwtToken2(authentication);
//        System.out.println("sign in: "+jwt);
//        AccountDTO userDetails = (AccountDTO) authentication.getPrincipal();
//        List<String> roles = userDetails.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .toList();
//        System.out.println("roles: "+roles);
//        return userDetails;
//    }
//}