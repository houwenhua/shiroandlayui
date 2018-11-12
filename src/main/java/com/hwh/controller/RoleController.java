package com.hwh.controller;

import com.hwh.po.UserRole;
import com.hwh.service.RoleService;
import com.hwh.vo.DataTable;
import com.hwh.vo.PermissionMenuVo;
import com.hwh.vo.RoleVo;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述：
 *
 * @Author houwenhua
 * @Date 2018/11/3 23:04
 */
@Controller
@RequestMapping("/roleController")
public class RoleController {

    @Autowired
    private RoleService rs;

    @RequestMapping(value = "/findAllRole",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public DataTable findAllRole(String name,int page,int limit) {
        DataTable dataTables = rs.getAllRoles(name,page,limit);
        return  dataTables;
    }

    @RequiresRoles("用户管理员")
    @RequestMapping(value = "/add",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String add(RoleVo rv) {
        rs.addRole(rv);
        return "1";
    }

    @RequiresRoles("用户管理员")
    @RequestMapping(value = "/delete",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String delete(String id) {
        List<UserRole> ur = rs.getUserRoleByRoleId(id);
        //如果没有用户使用该角色，就可以删除
        if(ur == null || ur.size() <= 0) {
            rs.deleteRole(id);
        } else {
            return "555";
        }
        return "1";
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String update(RoleVo rv) {
        rs.update(rv);
        return "1";
    }

    @RequestMapping(value = "/updateAvailable",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String updateAvailable(String id,String available) {
        rs.updateAvailable(id,available);
        return "1";
    }

    @RequestMapping(value = "/batchDelete",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String batchDelete(String ids) {
        rs.batchDelete(ids);
        return "1";
    }

    @RequestMapping(value = "/findPermissionMenu",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<PermissionMenuVo> findPermissionMenu(String id) {
       /* List<PermissionMenuVo> list = new ArrayList<>();
        PermissionMenuVo pv1 = new PermissionMenuVo("1","0","菜单一");
        PermissionMenuVo pv11 = new PermissionMenuVo("11", "1","菜单一一");
        PermissionMenuVo pv12 = new PermissionMenuVo("12","1","菜单一二");
        pv12.setChecked(true);

        list.add(pv1);
        list.add(pv11);
        list.add(pv12);*/

        List<PermissionMenuVo> pvList = rs.findPermissionMenu(id);
        return pvList;
    }

    @RequestMapping(value = "/addRolePermission",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String addRolePermission(String roleid,String ids) {
        rs.addRolePermission(roleid,ids);
        return "1";
    }
}
