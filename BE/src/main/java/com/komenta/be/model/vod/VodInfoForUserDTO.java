package com.komenta.be.model.vod;

public class VodInfoForUserDTO {
    private int v_id;
    private String v_title;
    private String g_name;
    private String gd_name;
    private String v_actors;
    private String v_director;
    private int ve_id;
    private int ve_episode_num;

    public VodInfoForUserDTO() {
    }

    public VodInfoForUserDTO(int v_id, String v_title, String g_name, String gd_name, String v_actors, String v_director,
                             int ve_id, int ve_episode_num) {
        this.v_id = v_id;
        this.v_title = v_title;
        this.g_name = g_name;
        this.gd_name = gd_name;
        this.v_actors = v_actors;
        this.v_director = v_director;
        this.ve_id = ve_id;
        this.ve_episode_num = ve_episode_num;
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

    @Override
    public String toString() {
        return "VodInfoForUserDTO{" +
                "v_id=" + v_id +
                ", v_title='" + v_title + '\'' +
                ", g_name='" + g_name + '\'' +
                ", gd_name='" + gd_name + '\'' +
                ", v_actors='" + v_actors + '\'' +
                ", v_director='" + v_director + '\'' +
                ", ve_id=" + ve_id +
                ", ve_episode_num=" + ve_episode_num +
                '}';
    }
}
