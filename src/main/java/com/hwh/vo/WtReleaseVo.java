package com.hwh.vo;

/**
 * @author hwh
 * @create 2019/4/10 16:22
 */
public class WtReleaseVo {

    private String wtid;

    private String sysname;

    private String title;

    private String releasedate;

    private String content;

    public WtReleaseVo() {
    }

    public WtReleaseVo(String sysname, String title, String releasedate) {
        this.sysname = sysname;
        this.title = title;
        this.releasedate = releasedate;
    }

    public WtReleaseVo(String wtid, String sysname, String title, String releasedate) {
        this.wtid = wtid;
        this.sysname = sysname;
        this.title = title;
        this.releasedate = releasedate;
    }

    public WtReleaseVo(String wtid, String sysname, String title, String releasedate, String content) {
        this.wtid = wtid;
        this.sysname = sysname;
        this.title = title;
        this.releasedate = releasedate;
        this.content = content;
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

    public String getWtid() {
        return wtid;
    }

    public void setWtid(String wtid) {
        this.wtid = wtid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
