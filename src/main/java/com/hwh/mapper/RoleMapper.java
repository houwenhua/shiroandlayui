package com.hwh.mapper;

import com.hwh.po.Role;
import com.hwh.po.UserRole;
import org.apache.ibatis.annotations.Param;
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

    List<Role> getUserAllRole(String id);

    List<Role> getAllRolesPage(@Param("name") String name,@Param("page") int page,@Param("limit") int limit);

    //void addRole(@Param("r") Role r);

    void addRole(@Param("id") String id,@Param("name") String name,@Param("available1") String available1);

    void deleteRole(String id);

    List<UserRole> getUserRoleByRoleId(String id);

    void update(Role r);

    void batchDelete(String ids);

    void updateAvailable(@Param("id") String id,@Param("available") String available);

    Long count(@Param("name") String name);
}
