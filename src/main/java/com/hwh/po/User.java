package com.hwh.po;

/**
 * @author hwh
 * @create 2018/9/25 13:40
 */
public class User {
    private String id;

    private String usercode;

    private String username;

    private String password;

    private String salt;

    private String locked;

    public User() {
    }

    public User(String username, String password, String salt, String locked) {
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.locked = locked;
    }

    public User(String id, String usercode, String username, String password, String salt, String locked) {
        this.id = id;
        this.usercode = usercode;
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.locked = locked;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getLocked() {
        return locked;
    }

    public void setLocked(String locked) {
        this.locked = locked;
    }
}
