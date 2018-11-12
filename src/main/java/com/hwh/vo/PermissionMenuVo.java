package com.hwh.vo;

/**
 * @author hwh
 * @create 2018/11/12 11:43
 */
public class PermissionMenuVo {
    private String id;

    private String pid;

    private String name;

    private Boolean checked;

    public PermissionMenuVo(String id, String pid, String name) {
        this.id = id;
        this.pid = pid;
        this.name = name;
    }

    public PermissionMenuVo(String id, String name, Boolean checked) {
        this.id = id;
        this.name = name;
        this.checked = checked;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}
