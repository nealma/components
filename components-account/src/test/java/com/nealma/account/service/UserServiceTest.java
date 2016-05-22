package com.nealma.account.service;

import com.nealma.framework.commons.StringUtil;
import com.nealma.framework.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by neal.ma on 5/17/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:account-spring.xml", "classpath:account-mybatis-mapper-config.xml", "classpath:account-mapper/UserMapper.xml"})
@PropertySource("classpath:account-config.properties")
@ActiveProfiles("development")
public class UserServiceTest {

    public static final Logger LOGGER = LoggerFactory.getLogger(UserServiceTest.class);

    @Autowired
    UserService userService;

    @Test
    public void insertTest() throws Exception {
        User user = createUser();
        userService.insert(user);
        LOGGER.debug("{}", "inserted");
    }

    @Test
    public void updateTest() throws Exception {
        User user = createUser();
        user.setId(1L);
        userService.update(user);
        LOGGER.debug("{}", "updated.");
    }

    @Test
    public void fetchTest() throws Exception {
        User user = userService.fetch(1L);
        LOGGER.debug("{}", user);
        LOGGER.debug("{}", "fetched.");
    }

    @Test
    public void fetchByUsernameTest() throws Exception {
        User user = userService.fetchByUsername("zhangsan");
        LOGGER.debug("{}", user);
        LOGGER.debug("{}", "fetchByUsername.");
    }

    @Test
    public void listTest() throws Exception {
        List<User> user = userService.list();
        LOGGER.debug("{}", user);
    }

    User createUser() {
        User user = new User();
        user.setUsername("zhangsan");
        user.setPassword("******");
        user.setSalt("****");
        return user;
    }

    @Test
    public void stringEmptyTest() {
        StringUtil.isEmpty("");
        LOGGER.debug("random=", ThreadLocalRandom.current().nextInt(10));
//        LOGGER.debug();
    }
}
