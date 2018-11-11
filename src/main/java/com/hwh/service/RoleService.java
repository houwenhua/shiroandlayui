package com.hwh.service;

import com.hwh.mapper.RoleMapper;
import com.hwh.mapper.UserMapper;
import com.hwh.po.Role;
import com.hwh.po.UserRole;
import com.hwh.vo.DataTable;
import com.hwh.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hwh
 * @create 2018/10/12 13:26
 */
@Service
public class RoleService {

    @Autowired
    private RoleMapper rm;

    @Autowired
    private UserMapper um;

    public List<RoleVo> getAllRole() {
        List<Role> list = rm.getAllRole();
        List<RoleVo> rlList = new ArrayList<>();
        for(Role role : list) {
            RoleVo rv = new RoleVo(role.getId(),role.getName());
            rlList.add(rv);
        }
        return rlList;
    }

    public DataTable getAllRoles(String name, int page, int limit) {
        //List<Role> list = rm.getAllRole();
        List<Role> list = rm.getAllRolesPage(name,(page - 1) * limit,limit);
        //Long count = new Long(list.size());
        Long count = rm.count(name);
        DataTable dataTable = new DataTable(0,"",count,list);
        return dataTable;
    }

    public List<String> getUserAllRole(String id) {
        List<Role> list = rm.getUserAllRole(id);
        List<String> strList = new ArrayList<>();
        for(Role role : list) {
            strList.add(role.getId());
        }
        return  strList;
    }

    public void addRole(RoleVo rv) {
        Role r = new Role(rv.getId(),rv.getName(),rv.getAvailable());
        rm.addRole(rv.getId(),rv.getName(),rv.getAvailable());
        //默认设置admin管理员拥有所有角色
        //um.addUserRoles("admin",rv.getId().toString());
    }

    public void deleteRole(String id) {
        rm.deleteRole(id);
    }

    public List<UserRole> getUserRoleByRoleId(String id) {
        List<UserRole> ur = rm.getUserRoleByRoleId(id);
        return ur;
    }

    public void update(RoleVo rv) {
        Role r = new Role(rv.getId(),rv.getName(),rv.getAvailable());
        rm.update(r);
    }

    /**
     * 批量删除字符串的话必须传入加上单引号的字符串（‘2’，‘1’）
     * @param ids
     */
    public void batchDelete(String ids) {
        String[] idArr = ids.split(",");
        for(String str : idArr){
            deleteRole(str);
        }

        /*String id = "";
        for(String str : idArr) {
            id = id + "," + "'" + str + "'";
        }
        ids = id.substring(1);
        ids = "(" + ids +")";
        rm.batchDelete(ids);*/
    }

    public void updateAvailable(String id,String available) {
        rm.updateAvailable(id,available);
    }
}
