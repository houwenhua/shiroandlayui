package com.hwh.controller;

import com.hwh.service.PermissionService;
import com.hwh.vo.DataTable;
import com.hwh.vo.PermissionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String add(PermissionVo pv){
        ps.addPermission(pv);
        return "1";
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String delete(String id) {
        ps.deletePermission(id);
        return "1";
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String update(PermissionVo pv) {
        ps.update(pv);
        return "1";
    }

    @RequestMapping(value = "/updateAvailable",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String updateAvailable(String id,String available) {
        ps.updateAvailable(id,available);
        return "1";
    }

    @RequestMapping(value = "/batchDelete",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String batchDelete(String ids) {
        ps.batchDelete(ids);
        return "1";
    }
}
