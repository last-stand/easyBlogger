package com.easyblogger.Service.user;

import com.easyblogger.EasyBloggerApplication;
import com.easyblogger.model.Authority;
import com.easyblogger.model.UserInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;

import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@Rollback(true)
@ActiveProfiles("test")
@TestPropertySource("/application-test.properties")
@SpringApplicationConfiguration(classes = {EasyBloggerApplication.class })
public class UserServiceImplTest{

    @Autowired
    private UserService userService;

    @Before
    public void setUp(){
        UserInfo userInfo = new UserInfo();
        userInfo.setEmail("abc@email.com");
        userInfo.setUsername("james");
        userInfo.setPassword("password");
        userInfo.setAuthority(Authority.ROLE_USER.toString());
        userInfo.setEnabled(true);
        userInfo.setCreated_at(new Date());
        userInfo.setUpdated_at(new Date());
        userService.createUser(userInfo);
    }

    @Test
    public void testGetAllUsers() throws Exception {
        Collection<UserInfo> users = userService.getAllUsers();
        assertEquals(users.size(), 1);

        List userList = new ArrayList(users);
        UserInfo user = (UserInfo) userList.get(0);
        assertEquals(user.getEmail(),"abc@email.com");
        assertEquals(user.getUsername(),"james");
        assertEquals(user.getPassword(),"password");
        assertEquals(user.getAuthority(),"USER");
        assertEquals(user.isEnabled(),true);
    }
}