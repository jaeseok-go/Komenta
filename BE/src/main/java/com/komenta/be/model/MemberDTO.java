package com.komenta.be.model;


public class MemberDTO{

    private String userid;
    private String userpw;
    private String username;
    private String email;
    private String address;

    public MemberDTO(String userid, String userpw, String username, String email, String address) {
        this.userid = userid;
        this.userpw = userpw;
        this.username = username;
        this.email = email;
        this.address = address;
    }

    public String getUserid() {
        return userid;
    }

    public String getUserpw() {
        return userpw;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setUserpw(String userpw) {
        this.userpw = userpw;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "MemberDTO{" +
                "userid='" + userid + '\'' +
                ", userpw='" + userpw + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}