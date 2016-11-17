package com.easyblogger.Service.user;

import com.easyblogger.model.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class AuthenticationService implements UserDetailsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationService.class);

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userInfo = userService.getUserByName(username);
        UserInfo user = userInfo.get();
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getAuthority().toString());
        UserDetails userDetails = (UserDetails)new User(user.getUsername(), user.getPassword(), Arrays.asList(authority));
        return userDetails;
    }
}
