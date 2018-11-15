package com.sutian.goldassist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: 思进
 * @Description:
 * @Date: 上午12:37 2018/11/16
 */
@Controller
@RequestMapping("app")
public class AppController {
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String hello() {
       return "hello word!";
    }
}
