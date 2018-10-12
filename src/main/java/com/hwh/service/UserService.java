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
}
