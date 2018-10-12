package com.hwh.vo;

import java.util.List;

/**
 * @author hwh
 * @create 2018/10/9 11:20
 */
public class MenusVo {

    private Integer id;

    private String text;

    private String icon;

    private String href;

    private List<MenusVo> subset;

    public MenusVo() {
    }

    public MenusVo(Integer id, String text, String icon, String href, List<MenusVo> subset) {
        this.id = id;
        this.text = text;
        this.icon = icon;
        this.href = href;
        this.subset = subset;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public List<MenusVo> getSubset() {
        return subset;
    }

    public void setSubset(List<MenusVo> subset) {
        this.subset = subset;
    }
}
