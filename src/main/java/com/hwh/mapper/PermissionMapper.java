package com.hwh.mapper;

import com.hwh.po.Permission;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @author hwh
 * @create 2018/9/26 10:51
 */
@Repository
public interface PermissionMapper {

    Set<Permission> getPermissionByUserCode(String usercode);
}
