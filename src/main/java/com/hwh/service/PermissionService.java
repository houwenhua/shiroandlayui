package com.hwh.service;

import com.hwh.mapper.PermissionMapper;
import com.hwh.po.Permission;
import com.hwh.po.Role;
import com.hwh.vo.DataTable;
import com.hwh.vo.PermissionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 功能描述：
 *
 * @Author houwenhua
 * @Date 2018/11/8 20:42
 */
@Service
public class PermissionService {

    @Autowired
    private PermissionMapper pm;

    public DataTable getAllPermissions(String name, int page, int limit) {
        List<Permission> list = pm.getAllPermissionsPage(name,(page - 1) * limit,limit);
        //获得行数
        Long count = pm.countPermissions(name);
        DataTable dataTable = new DataTable(0,"",count,list);
        return dataTable;
    }

    public void addPermission(PermissionVo pv) {
        Permission p = new Permission(pv.getName(),pv.getType(),pv.getUrl(),pv.getPercode(),pv.getParentid(),pv.getParentids(),pv.getSortstring(),pv.getAvailable());
        pm.addPermission(p);
    }

    public void deletePermission(String id) {
        pm.deletePermission(id);
    }

    public void update(PermissionVo pv) {
        Permission p = new Permission(pv.getName(),pv.getType(),pv.getUrl(),pv.getPercode(),pv.getParentid(),pv.getParentids(),pv.getSortstring(),pv.getAvailable());
        p.setId(pv.getId());
        pm.update(p);
    }

    public void batchDelete(String ids) {
        pm.batchDelete(ids);
    }

    public void updateAvailable(String id, String available) {
        pm.updateAvailable(id,available);
    }
}
