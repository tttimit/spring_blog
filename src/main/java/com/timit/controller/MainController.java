package com.timit.controller;

import com.timit.exception.DataAccessException;
import com.timit.exception.DataIntegrityViolationException;
import com.timit.bean.Post;
import com.timit.bean.PostList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.SQLException;

/**
 * Created by timit on 2017/3/3.
 */
@Controller
@RequestMapping("/")
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @RequestMapping
    public String index(Model model) {
        model.addAttribute("posts", PostList.getPosts());
        return "index";
    }

    @RequestMapping("posts")
    public String posts() {
        return "redirect:/";
    }

    @RequestMapping("posts/{id}")
    public String post(@PathVariable("id") long id, Model model) {
        Post post = PostList.getPostById(id);
//        if(post == null) throw new PostNotFoundException("post not found");
        model.addAttribute("post", post);
        return "post";
    }

//    @RequestMapping(value = "/posts", method = RequestMethod.POST)
//    public String create(@RequestParam("title") String title,
//                         @RequestParam("content") String content){
//        // 将标题和内容存储起来
//        return "post";
//    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String create(@Valid Post post, BindingResult result) {
        if (result.hasErrors()) {
            return "create";
        }

        Post p = PostList.add(post);
        return "redirect:posts/" + p.getId();
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createPost(Model model, HttpSession session) {
//  使用拦截器做了检查了
//        if (session.getAttribute("root") == null) {   //用户session中包含root才能访问创建新文章的页面
//            return "redirect:/";
//        }

        model.addAttribute("post", new Post());
        return "create";
    }

//    @RequestMapping(value = "/test", method = RequestMethod.GET)
//    public String testException() {
//        throw new RuntimeException("this is a test!");
//    }

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return container -> {
            container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/error/404"));
        };
    }

    // Exception handling methods

    @ResponseStatus(value = HttpStatus.CONFLICT, reason = "Data integrity violation") // 409
    @ExceptionHandler(DataIntegrityViolationException.class)
    public void confict() {
        //nothing to do
    }

    @ExceptionHandler({SQLException.class, DataAccessException.class})
    public String databaseError() {
        return "databaseError";
    }

    /**
     * 这个方法是总控制器
     *
     * @param req   产生错误的请求
     * @param exception 遇到的错误
     * @return  返回错误处理视图
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView handlerError(HttpServletRequest req, Exception exception) {
        logger.error("Request: " + req.getRequestURL() + " raised " + exception);

        ModelAndView mav = new ModelAndView();
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("error");
        return mav;

    }

//    @RequestMapping(value = "/secret_url", method = RequestMethod.GET)
//    public String showCreatePage(HttpSession session){
//        session.setAttribute("root", true);
//        return "redirect:/create/";
//    }

    public static void main(String[] args) {

    }
}
