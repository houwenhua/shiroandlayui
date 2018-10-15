package com.hwh.controller;

import com.hwh.po.Role;
import com.hwh.po.User;
import com.hwh.service.RoleService;
import com.hwh.service.UserService;
import com.hwh.vo.DataTable;
import com.hwh.vo.RoleVo;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author hwh
 * @create 2018/10/11 13:40
 */
@Controller
@RequestMapping("/userController")
public class UserController {

    @Autowired
    private UserService us;

    @Autowired
    private RoleService rs;


    @RequestMapping(value = "/findAllUser",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public DataTable findAllUser(String username,int page,int limit) {
        System.out.println(page+limit);
        DataTable dataTables = us.getAllUsers();
        return  dataTables;
    }


    @RequiresRoles("用户管理员")
    @RequestMapping(value = "/delete",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String delete(String id,String usercode) {
        return "1";
    }

    //@RequiresPermissions("user:add")
    @RequestMapping(value = "/add",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String add(User user,int userrole) {
        System.out.println(userrole);
        return "1";
    }


    @RequestMapping(value = "/findAllRole",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<RoleVo> findAllRole() {
        List<RoleVo> list = rs.getAllRole();
        return list;
    }
}
