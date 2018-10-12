package com.hwh.mapper;

import com.hwh.po.Role;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author hwh
 * @create 2018/9/26 10:37
 */
@Repository
public interface RoleMapper {

    Set<Role> getRoleByUserCode(String usercode);

    List<Role> getAllRole();
}
