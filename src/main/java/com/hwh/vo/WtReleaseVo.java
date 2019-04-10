package com.hwh.vo;

/**
 * @author hwh
 * @create 2019/4/10 16:22
 */
public class WtReleaseVo {

    private String sysname;

    private String title;

    private String releasedate;

    public WtReleaseVo() {
    }

    public WtReleaseVo(String sysname, String title, String releasedate) {
        this.sysname = sysname;
        this.title = title;
        this.releasedate = releasedate;
    }

    public String getSysname() {
        return sysname;
    }

    public void setSysname(String sysname) {
        this.sysname = sysname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(String releasedate) {
        this.releasedate = releasedate;
    }
}
