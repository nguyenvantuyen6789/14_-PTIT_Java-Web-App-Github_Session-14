package com.data.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {

    @GetMapping("account-detail")
    public String goLoginForm() {
        return "account_detail";
    }

}
