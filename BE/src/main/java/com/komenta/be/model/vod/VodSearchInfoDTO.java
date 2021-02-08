package com.komenta.be.model.vod;

public class VodSearchInfoDTO {
    private int v_id;
    private String v_title;
    private int ve_episode_num;
    private int ve_id;
    private int gd_id;

    public VodSearchInfoDTO() {
    }

    public VodSearchInfoDTO(int v_id, String v_title, int ve_episode_num, int ve_id, int gd_id) {
        this.v_id = v_id;
        this.v_title = v_title;
        this.ve_episode_num = ve_episode_num;
        this.ve_id = ve_id;
        this.gd_id = gd_id;
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

    public int getVe_episode_num() {
        return ve_episode_num;
    }

    public void setVe_episode_num(int ve_episode_num) {
        this.ve_episode_num = ve_episode_num;
    }

    public int getVe_id() {
        return ve_id;
    }

    public void setVe_id(int ve_id) {
        this.ve_id = ve_id;
    }

    public int getGd_id() {
        return gd_id;
    }

    public void setGd_id(int gd_id) {
        this.gd_id = gd_id;
    }

    @Override
    public String toString() {
        return "VodSearchInfoDTO{" +
                "v_id=" + v_id +
                ", v_title='" + v_title + '\'' +
                ", ve_episode_num=" + ve_episode_num +
                ", ve_id=" + ve_id +
                ", gd_id=" + gd_id +
                '}';
    }
}
