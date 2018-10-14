package com.hwh.po;

/**
 * @author hwh
 * @create 2018/9/25 13:43
 */
public class RolePermission {
    private Integer id;

    private String roleid;

    private Integer permissionid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    public Integer getPermissionid() {
        return permissionid;
    }

    public void setPermissionid(Integer permissionid) {
        this.permissionid = permissionid;
    }
}
