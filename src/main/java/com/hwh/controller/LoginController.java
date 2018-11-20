package com.hwh.controller;

import com.hwh.po.VisitLog;
import com.hwh.service.VisitService;
import com.hwh.util.DateUtil;
import com.hwh.util.UUIDUtil;
import com.hwh.vo.ActiveUser;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


/**
 * @author hwh
 * @create 2018/9/25 11:22
 */
@Controller
@RequestMapping("/loginController")
public class LoginController {

    @Autowired
    private VisitService vs;

    @RequestMapping(value = "/subLogin",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String subLogin(ActiveUser user) throws ParseException {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsercode(),user.getPassword());
        try{
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return "404";
        }
        //登录成功，产生一条访问日志信息
        VisitLog vl = new VisitLog(UUIDUtil.getUUID(), DateUtil.getFormatDate(new Date()),"登录系统",user.getUsercode());
        vs.add(vl);
        return "1";
    }

    /**
     * 退出登录
     * @return
     */
    @RequestMapping(value = "/logout",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public ModelAndView logout(HttpSession session) {
        session.invalidate();
        ModelAndView mav = new ModelAndView("redirect:/login.jsp");
        return mav;
    }

    @RequiresRoles("用户管理员")
    @RequestMapping(value = "/testRole")
    @ResponseBody
    public String testRole() {
        System.out.println("异常挑瓷砖***********");
        return "testRole success";
    }

    @RequiresRoles("用户管理员1")
    @RequestMapping(value = "/testRole1")
    @ResponseBody
    public String testRole1() {
        return "testRole1 success";
    }
}
