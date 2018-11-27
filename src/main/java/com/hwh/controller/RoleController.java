package com.hwh.controller;

import com.hwh.enums.ResultMessage;
import com.hwh.enums.ResultStatus;
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

    @RequiresRoles("用户管理员")
    @RequestMapping(value = "/findAllRole",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public DataTable findAllRole(String name,int page,int limit) {
        DataTable dataTables = rs.getAllRoles(name,page,limit);
        return  dataTables;
    }

    @RequiresRoles("用户管理员")
    @RequestMapping(value = "/add",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResultMessage add(RoleVo rv) {
        try {
            rs.addRole(rv);
        } catch (Exception e) {
            return new ResultMessage(ResultStatus.BAD_REQUEST,"异常：" + e.getMessage());
        }
        return new ResultMessage(ResultStatus.OK,"增加成功");
    }

    @RequiresRoles("用户管理员")
    @RequestMapping(value = "/delete",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResultMessage delete(String id) {
        try {
            List<UserRole> ur = rs.getUserRoleByRoleId(id);
            //如果没有用户使用该角色，就可以删除
            if(ur == null || ur.size() <= 0) {
                rs.deleteRole(id);
            } else {
                return new ResultMessage(ResultStatus.EXIST_USED_ROLE,"删除失败");
            }
        } catch (Exception e) {
            return new ResultMessage(ResultStatus.BAD_REQUEST,"异常：" + e.getMessage());
        }
        return new ResultMessage(ResultStatus.OK,"删除成功");
    }

    @RequiresRoles("用户管理员")
    @RequestMapping(value = "/update",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResultMessage update(RoleVo rv) {
        try {
            rs.update(rv);
        } catch (Exception e) {
            return new ResultMessage(ResultStatus.BAD_REQUEST,"异常：" + e.getMessage());
        }
        return new ResultMessage(ResultStatus.OK,"修改成功");
    }

    @RequiresRoles("用户管理员")
    @RequestMapping(value = "/updateAvailable",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResultMessage updateAvailable(String id,String available){
        List<UserRole> ur = rs.getUserRoleByRoleId(id);
        try {
            //如果没有用户使用该角色，就可以修改
            if(ur == null || ur.size() <= 0) {

            } else {
                return new ResultMessage(ResultStatus.EXIST_USED_ROLE,"删除失败");
            }
            rs.updateAvailable(id,available);
        } catch (Exception e) {
            return new ResultMessage(ResultStatus.BAD_REQUEST,"异常：" + e.getMessage());
        }
        return new ResultMessage(ResultStatus.OK,"修改成功");
    }

    @RequiresRoles("用户管理员")
    @RequestMapping(value = "/batchDelete",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResultMessage batchDelete(String ids) {
        try {
            String[] idArr = ids.split(",");
            //获得所有的用户角色
            for(String id : idArr) {
                List<UserRole> ur = rs.getUserRoleByRoleId(id);
                if(ur == null || ur.size() <= 0) {
                    rs.deleteRole(id);
                } else {
                    return new ResultMessage(ResultStatus.EXIST_USED_ROLE,"删除失败");
                }
            }
        } catch (Exception e) {
            return new ResultMessage(ResultStatus.BAD_REQUEST,"异常：" + e.getMessage());
        }
        //rs.batchDelete(ids);
        return new ResultMessage(ResultStatus.OK,"删除成功");
    }

    @RequiresRoles("用户管理员")
    @RequestMapping(value = "/findPermissionMenu",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<PermissionMenuVo> findPermissionMenu(String id) {

        List<PermissionMenuVo> pvList = rs.findPermissionMenu(id);
        return pvList;
    }

    @RequiresRoles("用户管理员")
    @RequestMapping(value = "/addRolePermission",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResultMessage addRolePermission(String roleid,String ids) {
        try {
            rs.addRolePermission(roleid,ids);
        } catch (Exception e) {
            return new ResultMessage(ResultStatus.BAD_REQUEST,"异常：" + e.getMessage());
        }
        return new ResultMessage(ResultStatus.OK,"操作成功");
    }
}
