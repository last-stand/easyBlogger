package com.easyblogger.Service.user;

import com.easyblogger.model.Authority;
import com.easyblogger.model.UserInfo;
import com.easyblogger.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserInfo createUser(UserInfo userInfo) {
        UserInfo model = new UserInfo();
        boolean enabled = true;
        model.setEmail(userInfo.getEmail());
        model.setUsername(userInfo.getUsername());
        model.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        model.setAuthority(Authority.ROLE_USER.toString());
        model.setEnabled(enabled);
        model.setCreated_at(new Date());
        model.setUpdated_at(new Date());
        return userRepository.saveAndFlush(model);
    }

    public UserInfo updateUser(int id, UserInfo userInfo) {
        UserInfo model = userRepository.findOne(id);
        if (model != null) {
            model.setUsername(userInfo.getUsername());
            model.setEmail(userInfo.getEmail());
            model.setPassword(userInfo.getPassword());
            return userRepository.saveAndFlush(model);
        }
        return null;
    }

    public void disableUser(int id) {
        boolean enabled = false;
        UserInfo model = userRepository.findOne(id);
        if (model != null) {
            model.setEnabled(enabled);
            userRepository.saveAndFlush(model);
        }
    }

    @Override
    public Collection<UserInfo> getAllUsers() {
        return userRepository.findAll(new Sort("id"));
    }

    @Override
    public Optional<UserInfo> getUserById(int id) {
        return Optional.ofNullable(userRepository.findOne(id));
    }

    @Override
    public Optional<UserInfo> getUserByEmail(String email) {
        return userRepository.findOneByEmail(email);
    }

    @Override
    public Optional<UserInfo> getUserByName(String username){
        return userRepository.findOneByUsername(username);
    }
}
