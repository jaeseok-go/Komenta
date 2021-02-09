package com.komenta.be.model.playlist;

public class PlayListDetailDTO {
    private int pl_id;
    private String pl_name;
    private String pl_comment;
    private int pl_good_count;
    private int u_id;
    private String u_nick_name;
    private String u_profile_pic;
    private int plc_id;
    private int v_id;
    private String v_title;
    private String v_actors;
    private String v_director;
    private String v_poster;
    private int ve_id;
    private int ve_episode_num;
    private int vh_id;
    private String vh_comment;
    private int vh_score;
    private String g_name;
    private String gd_name;

    public PlayListDetailDTO() {
    }

    public PlayListDetailDTO(int pl_id, String pl_name, String pl_comment, int pl_good_count, int u_id, String u_nick_name,
                             String u_profile_pic, int plc_id, int v_id, String v_title, String v_actors, String v_director,
                             String v_poster, int ve_id, int ve_episode_num, int vh_id, String vh_comment, int vh_score,
                             String g_name, String gd_name) {
        this.pl_id = pl_id;
        this.pl_name = pl_name;
        this.pl_comment = pl_comment;
        this.pl_good_count = pl_good_count;
        this.u_id = u_id;
        this.u_nick_name = u_nick_name;
        this.u_profile_pic = u_profile_pic;
        this.plc_id = plc_id;
        this.v_id = v_id;
        this.v_title = v_title;
        this.v_actors = v_actors;
        this.v_director = v_director;
        this.v_poster = v_poster;
        this.ve_id = ve_id;
        this.ve_episode_num = ve_episode_num;
        this.vh_id = vh_id;
        this.vh_comment = vh_comment;
        this.vh_score = vh_score;
        this.g_name = g_name;
        this.gd_name = gd_name;
    }

    public int getPl_id() {
        return pl_id;
    }

    public void setPl_id(int pl_id) {
        this.pl_id = pl_id;
    }

    public String getPl_name() {
        return pl_name;
    }

    public void setPl_name(String pl_name) {
        this.pl_name = pl_name;
    }

    public String getPl_comment() {
        return pl_comment;
    }

    public void setPl_comment(String pl_comment) {
        this.pl_comment = pl_comment;
    }

    public int getPl_good_count() {
        return pl_good_count;
    }

    public void setPl_good_count(int pl_good_count) {
        this.pl_good_count = pl_good_count;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public String getU_nick_name() {
        return u_nick_name;
    }

    public void setU_nick_name(String u_nick_name) {
        this.u_nick_name = u_nick_name;
    }

    public String getU_profile_pic() {
        return u_profile_pic;
    }

    public void setU_profile_pic(String u_profile_pic) {
        this.u_profile_pic = u_profile_pic;
    }

    public int getPlc_id() {
        return plc_id;
    }

    public void setPlc_id(int plc_id) {
        this.plc_id = plc_id;
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

    public String getV_actors() {
        return v_actors;
    }

    public void setV_actors(String v_actors) {
        this.v_actors = v_actors;
    }

    public String getV_director() {
        return v_director;
    }

    public void setV_director(String v_director) {
        this.v_director = v_director;
    }

    public String getV_poster() {
        return v_poster;
    }

    public void setV_poster(String v_poster) {
        this.v_poster = v_poster;
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

    public int getVh_id() {
        return vh_id;
    }

    public void setVh_id(int vh_id) {
        this.vh_id = vh_id;
    }

    public String getVh_comment() {
        return vh_comment;
    }

    public void setVh_comment(String vh_comment) {
        this.vh_comment = vh_comment;
    }

    public int getVh_score() {
        return vh_score;
    }

    public void setVh_score(int vh_score) {
        this.vh_score = vh_score;
    }

    public String getG_name() {
        return g_name;
    }

    public void setG_name(String g_name) {
        this.g_name = g_name;
    }

    public String getGd_name() {
        return gd_name;
    }

    public void setGd_name(String gd_name) {
        this.gd_name = gd_name;
    }


    @Override
    public String toString() {
        return "PlayListDetailDTO{" +
                "pl_id=" + pl_id +
                ", pl_name='" + pl_name + '\'' +
                ", pl_comment='" + pl_comment + '\'' +
                ", pl_good_count=" + pl_good_count +
                ", u_id=" + u_id +
                ", u_nick_name='" + u_nick_name + '\'' +
                ", u_profile_pic='" + u_profile_pic + '\'' +
                ", plc_id=" + plc_id +
                ", v_id=" + v_id +
                ", v_title='" + v_title + '\'' +
                ", v_actors='" + v_actors + '\'' +
                ", v_director='" + v_director + '\'' +
                ", v_poster='" + v_poster + '\'' +
                ", ve_id=" + ve_id +
                ", ve_episode_num=" + ve_episode_num +
                ", vh_id=" + vh_id +
                ", vh_comment='" + vh_comment + '\'' +
                ", vh_score=" + vh_score +
                ", g_name='" + g_name + '\'' +
                ", gd_name='" + gd_name + '\'' +
                '}';
    }
}
