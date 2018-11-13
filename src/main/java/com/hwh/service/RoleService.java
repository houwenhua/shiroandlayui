package com.hwh.service;

import com.hwh.mapper.PermissionMapper;
import com.hwh.mapper.RoleMapper;
import com.hwh.mapper.UserMapper;
import com.hwh.po.Permission;
import com.hwh.po.Role;
import com.hwh.po.UserRole;
import com.hwh.vo.DataTable;
import com.hwh.vo.PermissionMenuVo;
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

    @Autowired
    private PermissionMapper pm;

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
        //删除角色对应的role和资源信息
        rm.deleteRolePermission(id);
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


    /**
     * 查询所有的资源并选中角色已经具有的资源
     * @param id
     * @return
     */
    public List<PermissionMenuVo> findPermissionMenu(String id) {
        //先查询所有父节点parendid为0的根节点
       // List<Permission> list = rm.findPermissionByParendId(0);

        //查询用户具有的资源
        List<Permission> alist = rm.findPermissionByRoleId(id);

        //获得所有的资源
        List<Permission> list = pm.getAllPermissions();

        List<PermissionMenuVo> pvList = new ArrayList<>();
        for(Permission p : list) {
            PermissionMenuVo pv = new PermissionMenuVo(p.getId().toString(),p.getParentid(),p.getName());
            for(Permission pa : alist) {
                if(p.getId() == pa.getId()) {
                    pv.setChecked(true);
                }
            }
            pvList.add(pv);
        }
        return pvList;
    }

    /**
     * 增加角色的资源
     * @param ids
     */
    public void addRolePermission(String roleid,String ids) {
        //查询角色具有的资源
        List<Permission> alist = rm.findPermissionByRoleId(roleid);
        //构建角色具有资源的权限集合和一个临时集合temp
        List<String> rolePermissionIds = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        for(Permission p : alist) {
            rolePermissionIds.add(p.getId().toString());
            temp.add(p.getId().toString());
        }

        //获得前台菜单中的ids
        String[] idsList = ids.split(",");
        List<String> newList = new ArrayList<>();
        if(ids != "") {
            for(String str : idsList) {
                newList.add(str);
            }
        }

        //得到两个共有的集合部分，这部分不做任何改变
        temp.retainAll(newList);

        //获得已经具有的资源和共有部分的差集，得到已经删除的资源
        rolePermissionIds.removeAll(temp);
        //获得前台传过来的资源和共有部分的差集，得到新增的资源
        newList.removeAll(temp);

        //删除资源
        deleteRolePermissions(roleid,rolePermissionIds);
        //增加资源
        addRolePermissions(roleid,newList);

    }

    private void addRolePermissions(String roleid, List<String> newList) {
        for(String id : newList) {
            rm.addRolePermissions(roleid,id);
        }
    }

    private void deleteRolePermissions(String roleid, List<String> rolePermissionIds) {
        for(String id : rolePermissionIds) {
            rm.deleteRolePermissionsByRoleId(roleid,id);
        }
    }
}
