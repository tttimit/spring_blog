package com.timit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by timit on 2017/3/3.
 */
@Controller
@RequestMapping("/error")
public class ErrorController {
    @RequestMapping("/404")
    public String notFount() {
        return "error/404";
    }

    public static void main(String[] args) {

    }
}
