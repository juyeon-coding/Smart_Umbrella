package com.cookandroid.smart_umbrella;

public class User {
    private String profile;//String인 이유는 파이어 베이스에서 가져올때 URL주소로 가져옴
    private String id;
    private String pw;
    private String username;

    public User(){}


    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
