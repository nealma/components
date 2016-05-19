package com.nealma.account.rest;

import com.nealma.framework.domain.MessageBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by nealpc on 5/18/16.
 */
@RestController
@RequestMapping
public class AccountController {

    @RequestMapping("/accounts/login")
    public MessageBean login(@RequestParam(defaultValue = "", value = "username", required = true) String username,
                         @RequestParam(defaultValue = "", value = "password", required = true) String password) {
        MessageBean messageBean = new MessageBean();
        messageBean.setStatus(200);
        messageBean.setMessage("success");
        return messageBean;
    }
}
