package com.komenta.be.model.playlist;

public class PlayListGetAllDTO {
    private int pl_id;
    private int u_id;
    private int plc_id;
    private int vh_id;
    private int v_id;
    private String v_poster;
    private int ve_id;
    private int ve_episode_num;
    private String v_title;
    private String v_actors;
    private String v_director;

    private int pl_good_count;
    private String vh_watching_time;
    private String vh_comment;
    private int vh_score;
    private boolean vh_good;

    private String gd_id;
    private String g_name;
    private String gd_name;

    // 없어도 될 것 같은 녀석들
    private String pl_name;
    private String pl_comment;

    public PlayListGetAllDTO() {
    }

    public PlayListGetAllDTO(int pl_id, int u_id, int plc_id, int vh_id, int v_id, String v_poster,
                             int ve_id, int ve_episode_num, String v_title, String v_actors, String v_director,
                             int pl_good_count, String vh_watching_time, String vh_comment, int vh_score,
                             boolean vh_good, String gd_id, String g_name, String gd_name, String pl_name, String pl_comment) {
        this.pl_id = pl_id;
        this.u_id = u_id;
        this.plc_id = plc_id;
        this.vh_id = vh_id;
        this.v_id = v_id;
        this.v_poster = v_poster;
        this.ve_id = ve_id;
        this.ve_episode_num = ve_episode_num;
        this.v_title = v_title;
        this.v_actors = v_actors;
        this.v_director = v_director;
        this.pl_good_count = pl_good_count;
        this.vh_watching_time = vh_watching_time;
        this.vh_comment = vh_comment;
        this.vh_score = vh_score;
        this.vh_good = vh_good;
        this.gd_id = gd_id;
        this.g_name = g_name;
        this.gd_name = gd_name;
        this.pl_name = pl_name;
        this.pl_comment = pl_comment;
    }

    public int getPl_id() {
        return pl_id;
    }

    public void setPl_id(int pl_id) {
        this.pl_id = pl_id;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public int getPlc_id() {
        return plc_id;
    }

    public void setPlc_id(int plc_id) {
        this.plc_id = plc_id;
    }

    public int getVh_id() {
        return vh_id;
    }

    public void setVh_id(int vh_id) {
        this.vh_id = vh_id;
    }

    public int getV_id() {
        return v_id;
    }

    public void setV_id(int v_id) {
        this.v_id = v_id;
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

    public int getPl_good_count() {
        return pl_good_count;
    }

    public void setPl_good_count(int pl_good_count) {
        this.pl_good_count = pl_good_count;
    }

    public String getVh_watching_time() {
        return vh_watching_time;
    }

    public void setVh_watching_time(String vh_watching_time) {
        this.vh_watching_time = vh_watching_time;
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

    public boolean isVh_good() {
        return vh_good;
    }

    public void setVh_good(boolean vh_good) {
        this.vh_good = vh_good;
    }

    public String getGd_id() {
        return gd_id;
    }

    public void setGd_id(String gd_id) {
        this.gd_id = gd_id;
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

    @Override
    public String toString() {
        return "PlayListGetAllDTO{" +
                "pl_id=" + pl_id +
                ", u_id=" + u_id +
                ", plc_id=" + plc_id +
                ", vh_id=" + vh_id +
                ", v_id=" + v_id +
                ", v_poster='" + v_poster + '\'' +
                ", ve_id=" + ve_id +
                ", ve_episode_num=" + ve_episode_num +
                ", v_title='" + v_title + '\'' +
                ", v_actors='" + v_actors + '\'' +
                ", v_director='" + v_director + '\'' +
                ", pl_good_count=" + pl_good_count +
                ", vh_watching_time='" + vh_watching_time + '\'' +
                ", vh_comment='" + vh_comment + '\'' +
                ", vh_score=" + vh_score +
                ", vh_good=" + vh_good +
                ", gd_id='" + gd_id + '\'' +
                ", g_name='" + g_name + '\'' +
                ", gd_name='" + gd_name + '\'' +
                ", pl_name='" + pl_name + '\'' +
                ", pl_comment='" + pl_comment + '\'' +
                '}';
    }
}
