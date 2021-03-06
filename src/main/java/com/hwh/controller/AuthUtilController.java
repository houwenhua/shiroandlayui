package com.hwh.controller;

import com.hwh.enums.ResultMessage;
import com.hwh.enums.ResultStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 功能描述：
 *没有权限时，跳转的类
 * @Author houwenhua
 * @Date 2018/10/16 22:55
 */
@Controller
@RequestMapping("/authUtilController")
public class AuthUtilController {

    //没有权限返回的常量
    private final String noAuth = "444";

    /**
     * 用于注解权限没有权限时跳转
     * 没有授权跳转到********
     * @return
     */
    @RequestMapping(value = "/noAuthorizedException",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResultMessage noAuthorizedException(){
        System.out.println("没有授权跳转到，用于注解权限没有权限时跳转********");
        return new ResultMessage(ResultStatus.NO_AUTH,"没有操作权限");
    }

    /**
     * 用于配置文件没有权限时，跳转
     * @return
     */
    @RequestMapping(value = "/noAuthorizedException1",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResultMessage noAuthorizedException1(){
        System.out.println("没有授权跳转到，用于配置文件没有权限时，跳转********");
        return new ResultMessage(ResultStatus.NO_AUTH,"没有操作权限");
    }

    /**
     * 没有认证跳转到********
     * @return
     */
    @RequestMapping(value = "/noAuthenticatedException",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String noAuthenticatedException(){
        System.out.println("没有认证跳转到.............");
        return noAuth;
    }
}
