package com.timit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.sun.xml.internal.ws.api.message.Packet.Status.Request;

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

    @RequestMapping("/posts/")
    public String posts()
    {
        return "redirect:/";
    }

    @RequestMapping("/posts/{id}/")
    public String post(@PathVariable("id") long id, Model model){
        model.addAttribute("post", PostList.getPostById(id));
        return "post";
    }

//    @RequestMapping(value = "/posts", method = RequestMethod.POST)
//    public String create(@RequestParam("title") String title,
//                         @RequestParam("content") String content){
//        // 将标题和内容存储起来
//        return "post";
//    }

    @RequestMapping(value = "/posts/", method = RequestMethod.POST)
    public String create(@Valid Post post, BindingResult result){
        if(result.hasErrors()){
            return "create";
        }

        Post p = PostList.add(post);
        return "redirect:/posts/" + p.getId();
    }






    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createPost(Model model){
        model.addAttribute("post", new Post());
        return "create";
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
