package com.dapperdrakes.dimo.controller;


import com.dapperdrakes.dimo.config.JwtTokenUtil;
import com.dapperdrakes.dimo.model.DiMoUser;
import com.dapperdrakes.dimo.model.JwtResponse;
import com.dapperdrakes.dimo.model.UserDto;
import com.dapperdrakes.dimo.model.UserLoginRequest;
import com.dapperdrakes.dimo.service.IUserService;
import com.dapperdrakes.dimo.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class DiMoUserController {

    @Autowired
    IUserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @PostMapping(value = "/signup", produces = "application/json")
    public Object registerUserAccount(@Valid @RequestBody UserDto accountInfo) throws Exception {
        final DiMoUser registered = userService.registerNewUserAccount(accountInfo);
        if(registered != null) {
            return createAuthenticationToken(new UserLoginRequest(accountInfo.getEmail(), accountInfo.getPassword()));
        }
        return null;
    }

    @PostMapping(value = "/login" , produces = "application/json")
    public Object createAuthenticationToken(@RequestBody UserLoginRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return new JwtResponse(token);
    }

    private void authenticate(UserLoginRequest userLoginRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginRequest.getEmail(), userLoginRequest.getPassword()));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
