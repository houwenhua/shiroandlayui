package com.hwh.mapper;

import com.hwh.po.Permission;
import com.hwh.po.RolePermission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author hwh
 * @create 2018/9/26 10:51
 */
@Repository
public interface PermissionMapper {

    Set<Permission> getPermissionByUserCode(String usercode);

    List<Permission> getAllPermissionsPage(@Param("name") String name,@Param("page") int page,@Param("limit") int limit);

    Long countPermissions(@Param("name")String name);

    void addPermission(Permission p);

    void deletePermission(String id);

    void update(Permission p);

    void batchDelete(@Param("ids")ArrayList list);

    void updateAvailable(@Param("id")String id,@Param("available") String available);

    List<Permission> getAllPermissions();

    List<RolePermission> getRolePermissionByPermissionId(@Param("id")String id);
}
