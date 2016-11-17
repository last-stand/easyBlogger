package com.easyblogger.Service.user;

import com.easyblogger.model.UserInfo;

import java.util.Collection;
import java.util.Optional;

public interface UserService {
    Optional<UserInfo> getUserById(int id);
    Optional<UserInfo> getUserByEmail(String email);
    Optional<UserInfo> getUserByName(String username);

    Collection<UserInfo> getAllUsers();

    UserInfo createUser(UserInfo userInfo);
    UserInfo updateUser(int id, UserInfo userInfo);
    void disableUser(int id);
}
