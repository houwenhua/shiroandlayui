package com.hwh.service;

import com.hwh.mapper.UserMapper;
import com.hwh.po.User;
import com.hwh.vo.DataTable;
import com.hwh.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hwh
 * @create 2018/10/11 13:42
 */
@Service
public class UserService {

    @Autowired
    private UserMapper um;

    public DataTable getAllUsers() {
        //List<User> list = um.getAllUsers();
        List<UserVo> list1 = um.getAllUserVos();
        //封装数据到DataTable中
        List<UserVo> uvList = new ArrayList<>();
        for  ( int  i  =   0 ; i  <=  list1.size()  -   1 ; i ++ )  {
            String userroles = list1.get(i).getUserrole();
            UserVo uv = new UserVo(list1.get(i).getId(),list1.get(i).getUsercode(),list1.get(i).getUsername(),list1.get(i).getPassword(),list1.get(i).getSalt(),list1.get(i).getLocked(),list1.get(i).getUserrole());
            for  ( int  j  =  list1.size()  -   1 ; j  >  i; j -- )  {
                if  (list1.get(j).getId().equals(list1.get(i).getId()))  {
                    userroles = userroles + "," +list1.get(j).getUserrole();
                    list1.remove(j);
                }
            }
            uv.setUserrole(userroles);
            uvList.add(uv);
        }

        /*for(UserVo u : list1) {
            UserVo uv = new UserVo(u.getId(),u.getUsercode(),u.getUsername(),u.getPassword(),u.getSalt(),u.getLocked(),u.getUserrole());
            uvList.add(uv);
        }*/
        //获得数据记录数
        Long count = new Long(list1.size());
        DataTable dataTable = new DataTable(0,"",count,uvList);
        return dataTable;
    }

    /**
     * 重载查询全部
     * @param username
     * @param page
     * @param limit
     * @return
     */
    public DataTable getAllUsers(String username, int page, int limit) {
        List<UserVo> list1 = um.getPageAllUserVos(username,(page - 1) * limit,limit);
        //封装数据到DataTable中
        List<UserVo> uvList = new ArrayList<>();
        for  ( int  i  =   0 ; i  <=  list1.size()  -   1 ; i ++ )  {
            String userroles = list1.get(i).getUserrole();
            UserVo uv = new UserVo(list1.get(i).getId(),list1.get(i).getUsercode(),list1.get(i).getUsername(),list1.get(i).getPassword(),list1.get(i).getSalt(),list1.get(i).getLocked(),list1.get(i).getUserrole());
            for  ( int  j  =  list1.size()  -   1 ; j  >  i; j -- )  {
                if  (list1.get(j).getId().equals(list1.get(i).getId()))  {
                    userroles = userroles + "," +list1.get(j).getUserrole();
                    list1.remove(j);
                }
            }
            uv.setUserrole(userroles);
            uvList.add(uv);
        }
        //获得数据记录数
        Long count = new Long(list1.size());
        DataTable dataTable = new DataTable(0,"",count,uvList);
        return dataTable;
    }

    public String addUser(UserVo user) {
        //用户的id和usercode是相同的，而且usercode是不允许重复的
        List<User> list = um.getAllUsers();
        for(User user1 : list) {
            if(user1.getUsercode().equals(user.getUsercode())) {
                //返回5代表usercode已经存在
                return "5";
            }
        }
        User u = new User(user.getUsercode(),user.getUsercode(),user.getUsername(),user.getPassword(),user.getSalt(),user.getLocked());
        Integer flag = um.addUser(u);

        return flag.toString();
    }

    //增加用户角色
    private void addUserRole(String usercode, String userrole) {
        um.addUserRole(usercode,userrole);
    }

    //删除用户
    public void deleteUser(String id) {
        um.deleteUserById(id);
        um.deleteUserRoleByUserId(id);
    }

    /**
     * 批量删除用户和对应的角色信息
     * @param ids
     */
    public void deleteBatchUser(String ids) {
        String[] idArr = ids.split(",");
        for(int i = 0; i < idArr.length; i++) {
            deleteUser(idArr[i]);
        }
    }

    //增加角色信息
    public void addUserRoles(String id, String userrole) {
        String[] roleids = userrole.split(",");
        for(int i = 0; i < roleids.length; i++) {
            um.addUserRoles(id,roleids[i]);
        }
    }

    /**
     * 重载
     * @param id
     * @param urList
     */
    public void addUserRoles(String id, List<String> urList) {
        for(String role: urList) {
            um.addUserRoles(id,role);
        }
    }

    /**
     * 删除角色信息
     * @param id
     * @param roleids
     */
    public void deleteUserRoles(String id, List<String> roleids) {
        String ids = "";
        for(String role : roleids) {
            ids = ids + "," + role;
        }

        if(ids != "") {
            String temp = ids.substring(1,ids.length());
            ids = temp;
            um.dedeleteUserRoles(id,ids);
        }
    }

    /**
     * 修改方法,登录名禁止修改usercode
     * @param user
     * @return
     */
    public String updateUser(UserVo user) {
        User u = new User(user.getUsername(),user.getPassword(),user.getSalt(),user.getLocked());
        u.setId(user.getId());
        Integer flag = um.updateUser(u);
        return flag.toString();
    }

    public void updateLocked(String id, String locked) {
        um.updateLocked(id,locked);
    }
}
