package com.itc25.ticketingsystem.controllers;

import com.itc25.ticketingsystem.dtos.user.RegistrationDto;
import com.itc25.ticketingsystem.models.MyUserDetails;
import com.itc25.ticketingsystem.services.AuthService;
import com.itc25.ticketingsystem.services.MyUserDetailsService;
import com.itc25.ticketingsystem.utilities.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {

    private AuthService authService;
    private MyUserDetailsService myUserDetailsService;
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;

    @Autowired
    public AuthController(AuthService authService, MyUserDetailsService myUserDetailsService,
                          AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authService = authService;
        this.myUserDetailsService = myUserDetailsService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping
    public Authentication index(Authentication authentication){
        return authentication;
    }

    @GetMapping("admin")
    public String adminPanel(){
        return "<h1>Admin section</h1>";
    }

    @PostMapping("register")
    public String registration(@RequestBody RegistrationDto registrant){
        return authService.registration(registrant);
    }

    @PostMapping("jwt")
    public ResponseEntity<String> createAuthenticationToken(@RequestBody RegistrationDto credentials){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                credentials.getUsername(), credentials.getPassword()
        ));

        UserDetails userDetails = myUserDetailsService.loadUserByUsername(credentials.getUsername());

        String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(jwt);
    }
}
