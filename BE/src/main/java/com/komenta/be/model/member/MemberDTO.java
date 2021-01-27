package com.komenta.be.model.member;


public class MemberDTO{
    private int u_id;
    private String u_email;
    private String u_pw;
    private String u_phone_number;
    private String u_nickname;
    private String u_expire_member;
    private boolean u_is_blocked;
    private boolean u_is_admin;
    private String u_profile_pic;

    public MemberDTO() {
    }

    public MemberDTO(int u_id, String u_email, String u_pw) {
        this.u_id = u_id;
        this.u_email = u_email;
        this.u_pw = u_pw;
    }

    public MemberDTO(int u_id, String u_email, String u_pw, String u_phone_number, String u_nickname, String u_expire_member, boolean u_is_admin, boolean u_is_blocked, String u_profile_pic) {
        this.u_id = u_id;
        this.u_email = u_email;
        this.u_pw = u_pw;
        this.u_phone_number = u_phone_number;
        this.u_nickname = u_nickname;
        this.u_expire_member = u_expire_member;
        this.u_is_admin = u_is_admin;
        this.u_profile_pic = u_profile_pic;
        this.u_is_blocked = u_is_blocked;
    }

    public MemberDTO(String u_email, String u_pw, String u_phone_number, String u_nickname, String u_expire_member, boolean u_is_admin, boolean u_is_blocked, String u_profile_pic) {
        this.u_email = u_email;
        this.u_pw = u_pw;
        this.u_phone_number = u_phone_number;
        this.u_nickname = u_nickname;
        this.u_expire_member = u_expire_member;
        this.u_is_admin = u_is_admin;
        this.u_profile_pic = u_profile_pic;
        this.u_is_blocked = u_is_blocked;
    }

    public MemberDTO(String u_email, String u_pw, String u_phone_number, String u_nickname) {
        this.u_email = u_email;
        this.u_pw = u_pw;
        this.u_phone_number = u_phone_number;
        this.u_nickname = u_nickname;
    }


    public boolean isU_is_blocked() {
        return u_is_blocked;
    }

    public void setU_is_blocked(boolean u_is_blocked) {
        this.u_is_blocked = u_is_blocked;
    }

    // u_id는 setter 없음
    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public void setU_profile_pic(String u_profile_pic) {
        this.u_profile_pic = u_profile_pic;
    }

    public String getU_email() {
        return u_email;
    }

    public void setU_email(String u_email) {
        this.u_email = u_email;
    }

    public String getU_pw() {
        return u_pw;
    }

    public void setU_pw(String u_pw) {
        this.u_pw = u_pw;
    }

    public String getU_phone_number() {
        return u_phone_number;
    }

    public void setU_phone_number(String u_phone_number) {
        this.u_phone_number = u_phone_number;
    }

    public String getU_nickname() {
        return u_nickname;
    }

    public void setU_nickname(String u_nickname) {
        this.u_nickname = u_nickname;
    }

    public String getU_expire_member() {
        return u_expire_member;
    }

    public void setU_expire_member(String u_expire_member) {
        this.u_expire_member = u_expire_member;
    }

    public boolean isU_is_admin() {
        return u_is_admin;
    }

    public void setU_is_admin(boolean u_is_admin) {
        this.u_is_admin = u_is_admin;
    }

    public String getU_profile_pic() {
        return u_profile_pic;
    }

    public void setU_profile_ppic(String u_profile_pic) {
        this.u_profile_pic = u_profile_pic;
    }

    @Override
    public String toString() {
        return "MemberDTO{" +
                "u_id=" + u_id +
                ", u_email='" + u_email + '\'' +
                ", u_pw='" + u_pw + '\'' +
                ", u_phone_number='" + u_phone_number + '\'' +
                ", u_nickname='" + u_nickname + '\'' +
                ", u_expire_member='" + u_expire_member + '\'' +
                ", u_is_blocked=" + u_is_blocked +
                ", u_is_admin=" + u_is_admin +
                ", u_profile_pic='" + u_profile_pic + '\'' +
                '}';
    }
}