package com.hwh.service;

import com.hwh.mapper.RoleMapper;
import com.hwh.po.Role;
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

    public List<RoleVo> getAllRole() {
        List<Role> list = rm.getAllRole();
        List<RoleVo> rlList = new ArrayList<>();
        for(Role role : list) {
            RoleVo rv = new RoleVo(role.getId(),role.getName());
            rlList.add(rv);
        }
        return rlList;
    }
}
