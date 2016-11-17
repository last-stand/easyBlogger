package com.easyblogger.controller;

import com.easyblogger.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value="/api/authentication")
public class LoginController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    SecurityContextRepository repository;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity login(@RequestBody UserInfo authData, HttpServletRequest request, HttpServletResponse response) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(authData.getUsername(), authData.getPassword());
        SecurityContext securityContext = SecurityContextHolder.getContext();
        try {
            Authentication auth = authenticationManager.authenticate(token);
            securityContext.setAuthentication(auth);
            repository.saveContext(securityContext, request, response);
            return new ResponseEntity<UserInfo>((UserInfo)auth.getDetails(), HttpStatus.OK);
        } catch (BadCredentialsException ex) {
            return new ResponseEntity<UserInfo>(HttpStatus.UNAUTHORIZED);
        }


    }
}
