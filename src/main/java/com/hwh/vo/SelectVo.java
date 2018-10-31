package com.hwh.vo;

/**
 * @author hwh
 * @create 2018/10/31 11:31
 */
public class SelectVo {

    private String name;

    private String value;

    private String selected;

    private String disabled;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public SelectVo() {
    }

    public SelectVo(String name, String value, String selected, String disabled) {
        this.name = name;
        this.value = value;
        this.selected = selected;
        this.disabled = disabled;
    }
}
