package com.timit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by timit on 2017/3/1.
 */
@SpringBootApplication
@Controller
public class Application {

    @RequestMapping("/")
    public String index(Model model)
    {
        model.addAttribute("posts", PostList.getPosts());
        return "index";
    }

    @RequestMapping("/posts/{id}")
    public String post(@PathVariable("id") long id, Model model){
        model.addAttribute("post", PostList.getPostById(id));
        return "post";
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
