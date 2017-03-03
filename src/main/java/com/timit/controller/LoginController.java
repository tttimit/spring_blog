package com.timit.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * Created by timit on 2017/3/3.
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    public static final String USER_SESSION_ATTR = "AUTHED";

    public static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(method = RequestMethod.GET)
    public String loginPage(@RequestParam("next") Optional<String> next){
        logger.info("next= {}", next);
        return "login";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @PathVariable("next") Optional<String> next,
                        HttpSession session){
        if("admin".equals(username) && "password".equals(password))
            session.setAttribute(USER_SESSION_ATTR, true);

        return "redirect:".concat(next.orElse("/"));
    }

    public static void main(String[] args) {

    }
}
