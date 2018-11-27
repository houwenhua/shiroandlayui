package com.hwh.controller;

import com.hwh.enums.ResultMessage;
import com.hwh.enums.ResultStatus;
import com.hwh.po.Permission;
import com.hwh.po.RolePermission;
import com.hwh.service.PermissionService;
import com.hwh.vo.DataTable;
import com.hwh.vo.PermissionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 功能描述：
 *
 * @Author houwenhua
 * @Date 2018/11/8 20:38
 */
@Controller
@RequestMapping("/permissionController")
public class PermissionController {

    @Autowired
    private PermissionService ps;

    @RequestMapping(value = "/findAllPermission",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public DataTable findAllPermission(String name,int page,int limit) {
        DataTable dataTables = ps.getAllPermissions(name,page,limit);
        return  dataTables;
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResultMessage add(PermissionVo pv){
        try {
            ps.addPermission(pv);
        } catch (Exception e) {
            return new ResultMessage(ResultStatus.BAD_REQUEST,"异常：" + e.getMessage());
        }
        return new ResultMessage(ResultStatus.OK,"增加成功");
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResultMessage delete(String id) {
        try{
            //如果有角色使用就不能删除，获得是否有角色使用
            List<RolePermission> list = ps.getRolePermissionByPermissionId(id);
            if(list != null && list.size() > 0) {
                //有角色使用不能删除
                return new ResultMessage(ResultStatus.EXIST_USED_ROLE,"删除失败");
            }
            ps.deletePermission(id);
        }catch (Exception e) {
            return new ResultMessage(ResultStatus.BAD_REQUEST,"异常：" + e.getMessage());
        }
        return new ResultMessage(ResultStatus.OK,"删除成功");
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResultMessage update(PermissionVo pv) {
        try {
            ps.update(pv);
        } catch (Exception e) {
            return new ResultMessage(ResultStatus.BAD_REQUEST,"异常：" + e.getMessage());
        }
        return new ResultMessage(ResultStatus.OK,"修改成功");
    }

    @RequestMapping(value = "/updateAvailable",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResultMessage updateAvailable(String id,String available) {
        try {
            ps.updateAvailable(id,available);
        } catch (Exception e) {
            return new ResultMessage(ResultStatus.BAD_REQUEST,"异常：" + e.getMessage());
        }
        return new ResultMessage(ResultStatus.OK,"修改成功");
    }

    @RequestMapping(value = "/batchDelete",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResultMessage batchDelete(String ids) {
        try{
            List<RolePermission> list = ps.getBatchRolePermissionByPermissionId(ids);
            if(list != null && list.size() > 0) {
                //有角色使用不能删除
                return new ResultMessage(ResultStatus.EXIST_USED_ROLE,"删除失败");
            }
            ps.batchDelete(ids);
        }catch (Exception e) {
            return new ResultMessage(ResultStatus.BAD_REQUEST,"异常：" + e.getMessage());
        }
        return new ResultMessage(ResultStatus.OK,"删除成功");
    }
}
