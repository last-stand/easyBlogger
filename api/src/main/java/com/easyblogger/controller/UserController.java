package com.easyblogger.controller;

import com.easyblogger.Service.user.UserService;
import com.easyblogger.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(value="/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<UserInfo> findAll() {
        return userService.getAllUsers();
    }

    @RequestMapping(method = RequestMethod.POST)
    public UserInfo add(@RequestBody UserInfo userInfo) {
        return userService.createUser(userInfo);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<UserInfo> findOne(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public UserInfo update(@PathVariable int id, @RequestBody UserInfo userInfo) {
        return userService.updateUser(id, userInfo);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id) {
        userService.disableUser(id);
    }

}
