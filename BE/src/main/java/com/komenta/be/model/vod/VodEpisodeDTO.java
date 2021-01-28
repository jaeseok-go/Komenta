package com.komenta.be.model.vod;

public class VodEpisodeDTO {
    private int ve_id;
    private int v_id;
    private int ve_episode_num;
    private String ve_contents;
    private String ve_upload_date;
    private String ve_admin;

    public VodEpisodeDTO() {
    }

    public VodEpisodeDTO(int ve_id, int v_id, int ve_episode_num, String ve_contents, String ve_admin, String ve_upload_date) {
        this.ve_id = ve_id;
        this.v_id = v_id;
        this.ve_episode_num = ve_episode_num;
        this.ve_contents = ve_contents;
        this.ve_upload_date = ve_upload_date;
        this.ve_admin = ve_admin;
    }

    public VodEpisodeDTO(int v_id, int ve_episode_num, String ve_contents, String ve_upload_date, String ve_admin) {
        this.v_id = v_id;
        this.ve_episode_num = ve_episode_num;
        this.ve_contents = ve_contents;
        this.ve_upload_date = ve_upload_date;
        this.ve_admin = ve_admin;
    }

    public VodEpisodeDTO(int ve_id, int v_id, int ve_episode_num, String ve_contents) {
        this.ve_id = ve_id;
        this.v_id = v_id;
        this.ve_episode_num = ve_episode_num;
        this.ve_contents = ve_contents;
    }

    public void setVe_id(int ve_id) {
        this.ve_id = ve_id;
    }

    public String getVe_upload_date() {
        return ve_upload_date;
    }

    public void setVe_upload_date(String ve_upload_date) {
        this.ve_upload_date = ve_upload_date;
    }

    public String getVe_admin() {
        return ve_admin;
    }

    public void setVe_admin(String ve_admin) {
        this.ve_admin = ve_admin;
    }

    public int getVe_id() {
        return ve_id;
    }

    public int getV_id() {
        return v_id;
    }

    public void setV_id(int v_id) {
        this.v_id = v_id;
    }

    public int getVe_episode_num() {
        return ve_episode_num;
    }

    public void setVe_episode_num(int ve_episode_num) {
        this.ve_episode_num = ve_episode_num;
    }

    public String getVe_contents() {
        return ve_contents;
    }

    public void setVe_contents(String ve_contents) {
        this.ve_contents = ve_contents;
    }

    @Override
    public String toString() {
        return "VodEpisodeTableDTO{" +
                "ve_id=" + ve_id +
                ", v_id=" + v_id +
                ", ve_episode_num=" + ve_episode_num +
                ", ve_contents='" + ve_contents + '\'' +
                '}';
    }
}
