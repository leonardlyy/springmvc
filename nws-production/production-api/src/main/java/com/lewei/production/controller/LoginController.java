package com.lewei.production.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by 22901 on 2017/4/7.
 */
@Controller
public class LoginController {

    @RequestMapping("/")
    public String getWarehouse(@RequestParam(required = false) String companyNo){
        return "index";
    }
}
