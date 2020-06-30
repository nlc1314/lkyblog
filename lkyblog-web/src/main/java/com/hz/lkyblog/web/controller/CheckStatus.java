package com.hz.lkyblog.web.controller;

import com.hz.lkyblog.web.aspect.IgnoreLogin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping
public class CheckStatus {

    @IgnoreLogin
    @RequestMapping("/checkStatus")
    @ResponseBody
    public String test(){
        return "success";
    }
}
