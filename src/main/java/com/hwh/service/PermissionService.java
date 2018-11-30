package com.hwh.service;

import com.hwh.mapper.PermissionMapper;
import com.hwh.po.Permission;
import com.hwh.po.Role;
import com.hwh.po.RolePermission;
import com.hwh.vo.DataTable;
import com.hwh.vo.PermissionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述：
 *
 * @Author houwenhua
 * @Date 2018/11/8 20:42
 */
@Service
@Transactional
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

    public void addPermission(PermissionVo pv)throws Exception {
        Permission p = new Permission(pv.getName(),pv.getType(),pv.getUrl(),pv.getPercode(),pv.getParentid(),pv.getParentids(),pv.getSortstring(),pv.getAvailable());
        pm.addPermission(p);
    }

    public void deletePermission(String id) {
        pm.deletePermission(id);
    }

    public void update(PermissionVo pv) throws Exception{
        Permission p = new Permission(pv.getName(),pv.getType(),pv.getUrl(),pv.getPercode(),pv.getParentid(),pv.getParentids(),pv.getSortstring(),pv.getAvailable());
        p.setId(pv.getId());
        pm.update(p);
    }

    public void batchDelete(String ids) throws Exception{
        String[] idArr = ids.split(",");
        ArrayList list = new ArrayList<>();
        for(int i = 0; i < idArr.length; i++){
            list.add(Integer.parseInt(idArr[i]));
        }
        pm.batchDelete(list);
    }

    public void updateAvailable(String id, String available) throws Exception{
        pm.updateAvailable(id,available);
    }

    public List<RolePermission> getRolePermissionByPermissionId(String id) throws Exception{
        return pm.getRolePermissionByPermissionId(id);
    }

    public List<RolePermission> getBatchRolePermissionByPermissionId(String ids) throws Exception{
        String[] idArr = ids.split(",");
        for(String id : idArr) {
            List<RolePermission> list = getRolePermissionByPermissionId(id);
            if(list != null || list.size() > 0) {
                return list;
            }
        }
        return null;
    }
}
