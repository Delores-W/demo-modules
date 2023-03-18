package com.delores.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author William
 * @date 4/1/21 2:22 AM
 * @description
 */
//@SessionAttributes({"user","info"})
@Controller
public class DeloresController {

//    @RequestMapping("/wowww")
//    public String wowww(Map<String, String> map) {
//        map.put("message","Delores");
//
//        // 这个值会保存在SessionAttribute中
//        map.put("user","uu");
//        map.put("info", "test info");
//
//        return "hello";
//    }


    @RequestMapping("/test")
    public String test() {
        System.out.println("invoke test method");
        return "hello";
    }

    // /tes1?name=delores&age=20
    @RequestMapping(value = "/test1", params = {"name", "age"})
    public String test1() {
        System.out.println("invoke params methond");
        return "hello";
    }

    // /param?user=delores&pwd=123456
    @RequestMapping(value = "/param")
    public void param(HttpServletRequest request, HttpServletResponse response) {
        String user = request.getParameter("user");
        String pwd = request.getParameter("pwd");
        System.out.println(user);
        System.out.println(pwd);
    }

    // /param?user=delores&pwd=123456 默认 return "param2"
    // JSP file [/WEB-INF/pages/param2.jsp] not found
    @RequestMapping(value = "/param2")
    public void param2(@RequestParam(value = "user", required = false, defaultValue = "william") String userName, @RequestParam("pwd") String password) {
        System.out.println(userName);
        System.out.println(password);
    }

    // spring 3.0 占位符
    // /test/delores 不用username=delores
    // 可以映射URL中的占位符 到目标方法的参数！！！！！！ 为支持REST提供了基础
    @RequestMapping("/test2/{username}")
    public String test2(@PathVariable("username") String username) {
        System.out.println("invoke params methond");
        System.out.println(username);
        return "hello";
    }

    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    public ModelAndView hi() {
        ModelAndView modelAndView = new ModelAndView("hello");
//        model.addAttribute("message","Hello Spring MVC Framework!");
        modelAndView.addObject("message", "Hi Spring MVC Framework!");

        return modelAndView;
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(ModelMap model) {
        model.addAttribute("message", "Hello Spring MVC Framework!");

        return "hello";
    }

    @RequestMapping("/wow")
    public String wow(Map<String, String> map) {
        map.put("message","Delores");
        return "hello";
    }

    @RequestMapping("/wow2")
    public String wow2(Map<String, String> map) {
        map.put("message","Delores");
        return "hello";
    }





//    @RequestMapping("/redirect")
//    public String redirect() {
//        return "redirect:/index.jsp";
//    }


//    @RequestMapping("{name}")
//    public @ResponseBody
//    User getUser(@PathVariable String name) {
//
//        User user = new User();
//
//        user.setName(name);
//        user.setPassword("0105");
//        return user;
//    }


}
