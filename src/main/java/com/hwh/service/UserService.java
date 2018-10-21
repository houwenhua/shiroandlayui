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
        List<User> list = um.getAllUsers();
        //封装数据到DataTable中
        List<UserVo> uvList = new ArrayList<>();
        for(User u : list) {
            UserVo uv = new UserVo(u.getId(),u.getUsercode(),u.getUsername(),u.getPassword(),u.getSalt(),u.getLocked());
            uvList.add(uv);
        }
        //获得数据记录数
        Long count = new Long(list.size());

        DataTable dataTable = new DataTable(0,"",count,uvList);
        return dataTable;
    }

    public String addUser(UserVo user, String userrole) {
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

        //增加用户角色
        addUserRole(user.getUsercode(),userrole);
        return flag.toString();
    }

    //增加用户角色
    private void addUserRole(String usercode, String userrole) {
        um.addUserRole(usercode,userrole);
    }
}
