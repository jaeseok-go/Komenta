package com.komenta.be.model.admin;

public class ReportListDTO {
    private int r_id;
    private String r_type;
    private String r_contents;
    private int cu_id;
    private int u_id;
    private String u_email;
    private String u_pw;
    private String u_phone_number;
    private String u_nickname;
    private String u_expire_member;
    private boolean u_is_admin;
    private boolean u_is_blocked;
    private String u_profile_pic;

    public ReportListDTO(){
    }

    public ReportListDTO(String r_type, String r_contents, int cu_id, int u_id, String u_email, String u_pw, String u_phone_number, String u_nickname, String u_expire_member, boolean u_is_admin, boolean u_is_blocked, String u_profile_pic) {
        this.r_type = r_type;
        this.r_contents = r_contents;
        this.cu_id = cu_id;
        this.u_id = u_id;
        this.u_email = u_email;
        this.u_pw = u_pw;
        this.u_phone_number = u_phone_number;
        this.u_nickname = u_nickname;
        this.u_expire_member = u_expire_member;
        this.u_is_admin = u_is_admin;
        this.u_is_blocked = u_is_blocked;
        this.u_profile_pic = u_profile_pic;
    }


    public int getR_id() {
        return r_id;
    }

    public String getR_type() {
        return r_type;
    }

    public void setR_type(String r_type) {
        this.r_type = r_type;
    }

    public String getR_contents() {
        return r_contents;
    }

    public void setR_contents(String r_contents) {
        this.r_contents = r_contents;
    }

    public int getCu_id() {
        return cu_id;
    }

    public void setCu_id(int cu_id) {
        this.cu_id = cu_id;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
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

    public boolean isU_is_blocked() {
        return u_is_blocked;
    }

    public void setU_is_blocked(boolean u_is_blocked) {
        this.u_is_blocked = u_is_blocked;
    }

    public String getU_profile_pic() {
        return u_profile_pic;
    }

    public void setU_profile_pic(String u_profile_pic) {
        this.u_profile_pic = u_profile_pic;
    }

    @Override
    public String toString() {
        return "ReportListDTO{" +
                "r_id=" + r_id +
                ", r_type='" + r_type + '\'' +
                ", r_contents='" + r_contents + '\'' +
                ", cu_id=" + cu_id +
                ", u_id=" + u_id +
                ", u_email='" + u_email + '\'' +
                ", u_pw='" + u_pw + '\'' +
                ", u_phone_number='" + u_phone_number + '\'' +
                ", u_nickname='" + u_nickname + '\'' +
                ", u_expire_member='" + u_expire_member + '\'' +
                ", u_is_admin=" + u_is_admin +
                ", u_is_blocked=" + u_is_blocked +
                ", u_profile_pic='" + u_profile_pic + '\'' +
                '}';
    }
}
