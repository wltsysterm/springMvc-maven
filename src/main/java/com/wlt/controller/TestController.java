package com.wlt.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class TestController {
    //1、以当前类.class初始化，会自动将类的全路径作为参数初始化log，那么过滤条件就是以该全路径作为条件，跟配置文件里面的配置做比较
//    private Logger logger = Logger.getLogger(TestController.class);
    //2、也可以手动设置路径字符串
    private Logger logger = Logger.getLogger("com.wlt.controller.TestController");
    @RequestMapping("do")
    @ResponseBody
    public void doSomething(){
        System.out.println("11111");
        logger.error("hahah");
    }

    @RequestMapping("do1")
    public ModelAndView doSomething1(){
        System.out.println("22222");
        return new ModelAndView("");
    }

    @RequestMapping("do2")
    @ResponseBody
    public String doSomething2(){
        System.out.println("11111");
        return "测试不返回页面";
    }

    @RequestMapping("index")
    public ModelAndView index(){
        return new ModelAndView("index");
    }

    @RequestMapping("/test/{id}")
    @ResponseBody
    public String rest(@PathVariable long id){
        return id+"";
    }
    /**
     * 使用@CookieValue 绑定cookie值<br/>
     * 注解@CookieValue 也有 value ,required ,defaultValue 三个参数
     * @param session
     * @return
     */
    @RequestMapping("cookieValue")
    @ResponseBody
    public String cookieValue(@CookieValue(value = "JSESSIONID", required= false)String session){
        System.out.println("JESSIONID:["+session+"]");
        return session;
    }
    /**
     * 获取请求头中的信息
     * @RequestHeader 也有 value ,required ,defaultValue 三个参数
     * @param userAgent
     * @param cookie
     * @return
     */
    @RequestMapping("/requestHeader")
    public String requestHeader(@RequestHeader("User-Agent")String userAgent,@RequestHeader("Cookie")String cookie){
        System.out.println("userAgent:["+userAgent+"]");
        System.out.println("cookie:["+cookie+"]");
        return "helloworld";
    }
    /**
     * 如果 required = true 则表示请求参数对应的 字段 必须存在.如果不存在则会抛出异常<br/>
     * @param firstName 可以为null
     * @param lastName 不能为null .为null报异常
     * @param age age字段表示如果没有 age 参数 则默认值为 0
     * @return
     */
    @RequestMapping("/requestParam")
    public String requestParam(@RequestParam(value="firstName",required=false)String firstName,
                               @RequestParam( value="lastName" ,required = true) String lastName,
                               @RequestParam(value="age",required = false ,defaultValue="0")int age) {
        System.out.println("hello my name is " + (firstName == null ? "" : firstName)
                + lastName + "," + age +" years old this year");
        return "helloworld";
    }
}
