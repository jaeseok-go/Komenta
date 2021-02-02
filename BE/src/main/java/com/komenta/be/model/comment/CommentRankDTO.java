package com.komenta.be.model.comment;

public class CommentRankDTO {
    private int rank;
    private int v_id;
    private String v_title;
    private int ve_id;
    private int ve_episode_num;
    private int c_id;
    private int c_good_count;
    private String c_contents;
    private String c_playtime;
    private int u_id;
    private String u_email;
    private String u_nickname;
    private String u_profile_pic;

    public CommentRankDTO() {
    }

    public CommentRankDTO(int rank, int v_id, String v_title, int ve_id, int ve_episode_num, int c_id, int c_good_count,
                          String c_contents, String c_playtime, int u_id, String u_email, String u_nickname, String u_profile_pic) {
        this.rank = rank;
        this.v_id = v_id;
        this.v_title = v_title;
        this.ve_id = ve_id;
        this.ve_episode_num = ve_episode_num;
        this.c_id = c_id;
        this.c_good_count = c_good_count;
        this.c_contents = c_contents;
        this.c_playtime = c_playtime;
        this.u_id = u_id;
        this.u_email = u_email;
        this.u_nickname = u_nickname;
        this.u_profile_pic = u_profile_pic;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getV_id() {
        return v_id;
    }

    public void setV_id(int v_id) {
        this.v_id = v_id;
    }

    public String getV_title() {
        return v_title;
    }

    public void setV_title(String v_title) {
        this.v_title = v_title;
    }

    public int getVe_id() {
        return ve_id;
    }

    public void setVe_id(int ve_id) {
        this.ve_id = ve_id;
    }

    public int getVe_episode_num() {
        return ve_episode_num;
    }

    public void setVe_episode_num(int ve_episode_num) {
        this.ve_episode_num = ve_episode_num;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public int getC_good_count() {
        return c_good_count;
    }

    public void setC_good_count(int c_good_count) {
        this.c_good_count = c_good_count;
    }

    public String getC_contents() {
        return c_contents;
    }

    public void setC_contents(String c_contents) {
        this.c_contents = c_contents;
    }

    public String getC_playtime() {
        return c_playtime;
    }

    public void setC_playtime(String c_playtime) {
        this.c_playtime = c_playtime;
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
        return "CommentRankDTO{" +
                "rank=" + rank +
                ", v_id=" + v_id +
                ", v_title='" + v_title + '\'' +
                ", ve_id=" + ve_id +
                ", ve_episode_num=" + ve_episode_num +
                ", c_id=" + c_id +
                ", c_good_count=" + c_good_count +
                ", c_contents='" + c_contents + '\'' +
                ", c_playtime='" + c_playtime + '\'' +
                ", u_id=" + u_id +
                ", u_email='" + u_email + '\'' +
                ", u_nickname='" + u_nickname + '\'' +
                ", u_profile_pic='" + u_profile_pic + '\'' +
                '}';
    }
}
