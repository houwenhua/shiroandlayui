package com.hwh.vo;

/**
 * @author hwh
 * @create 2018/10/12 13:24
 */
public class RoleVo {

    private String id;

    private String name;

    private String available;

    public RoleVo() {
    }

    public RoleVo(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public RoleVo(String id, String name, String available) {
        this.id = id;
        this.name = name;
        this.available = available;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
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
}
