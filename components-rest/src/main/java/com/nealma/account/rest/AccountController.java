package com.nealma.account.rest;

import com.nealma.framework.model.MessageBean;
import com.nealma.framework.web.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by nealpc on 5/18/16.
 */
@RestController
public class AccountController extends BaseController {
    @RequestMapping("/accounts/login")
    public MessageBean login(@RequestParam(defaultValue = "", value = "username", required = true) String username,
                         @RequestParam(defaultValue = "", value = "password", required = true) String password) {
        MessageBean messageBean = new MessageBean();
        messageBean.setStatus(200);
        messageBean.setMessage("success");
        return messageBean;
    }

}
