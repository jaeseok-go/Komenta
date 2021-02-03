package com.komenta.be.model.follow;

public class FollowSelectDTO {
    private int f_id;
    private String u_nickname;
    private String u_profile_pic;

    public FollowSelectDTO(){}

    public FollowSelectDTO(int f_id, String u_nickname, String u_profile_pic) {
        this.f_id = f_id;
        this.u_nickname = u_nickname;
        this.u_profile_pic = u_profile_pic;
    }

    public int getF_id() {
        return f_id;
    }

    public void setF_id(int f_id) {
        this.f_id = f_id;
    }

    public String getU_nickname() {
        return u_nickname;
    }

    public void setU_nickname(String u_nickname) {
        this.u_nickname = u_nickname;
    }

    public String getU_profile_pic() {
        return u_profile_pic;
    }

    public void setU_profile_pic(String u_profile_pic) {
        this.u_profile_pic = u_profile_pic;
    }

    @Override
    public String toString() {
        return "FollowSelectDTO{" +
                "f_id=" + f_id +
                ", u_nickname='" + u_nickname + '\'' +
                ", u_profile_pic='" + u_profile_pic + '\'' +
                '}';
    }
}
