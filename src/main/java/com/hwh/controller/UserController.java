package com.hwh.controller;

import com.hwh.po.Role;
import com.hwh.po.User;
import com.hwh.service.RoleService;
import com.hwh.service.UserService;
import com.hwh.vo.DataTable;
import com.hwh.vo.RoleVo;
import com.hwh.vo.SelectVo;
import com.hwh.vo.UserVo;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        System.out.println("执行删除方法******");
        us.deleteUser(id);
        return "1";
    }

    @RequiresRoles("用户管理员")
    @RequestMapping(value = "/batchDelete",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String batchDelete(String ids) {
        System.out.println("执行删除方法******"+ids);
        return "1";
    }

    @RequiresRoles("用户管理员")
    @RequestMapping(value = "/add",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String add(UserVo user) {
        String flag = us.addUser(user);
        return "1";
    }


    @RequestMapping(value = "/findAllRole",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public DataTable findAllRole(String id) {
        List<RoleVo> list = rs.getAllRole();
        List<String> listroleids = findUserAllRole(id);

        List<SelectVo> svList = new ArrayList<>();
        for(RoleVo rv : list) {
            SelectVo sv = new SelectVo(rv.getName(),rv.getId(),"","");
            for(String str : listroleids) {
                if(str.equals(rv.getId())) {
                    sv.setSelected("selected");
                }
            }
            svList.add(sv);
        }
        DataTable dt = new DataTable(0,"",svList);
        return dt;
    }

    @RequestMapping(value = "/findUserAllRole",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<String> findUserAllRole(String id) {
        List<String> list = rs.getUserAllRole(id);
        return list;
    }

    /**
     * 增加用户角色
     */
    @RequiresRoles("用户管理员")
    @RequestMapping(value = "/addUserRoles",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String addUserRoles(String id,String userrole) {
        System.out.println(id + ":" + userrole);
        us.addUserRoles(id,userrole);
        return "1";
    }
}
