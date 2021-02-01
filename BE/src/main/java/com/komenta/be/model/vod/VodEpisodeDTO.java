package com.komenta.be.model.vod;

public class VodEpisodeDTO {
    private int ve_id;
    private int ve_episode_num;
    private String ve_contents;
    private String ve_upload_date;
    private String ve_admin;
    private int v_id;
    private String v_title;
    private int gd_id;
    private String g_name;
    private String gd_name;

    public VodEpisodeDTO() {
    }

    public VodEpisodeDTO(int ve_id, int ve_episode_num, String ve_contents, String ve_upload_date, String ve_admin, int v_id, String v_title, int gd_id, String g_name, String gd_name) {
        this.ve_id = ve_id;
        this.ve_episode_num = ve_episode_num;
        this.ve_contents = ve_contents;
        this.ve_upload_date = ve_upload_date;
        this.ve_admin = ve_admin;
        this.v_id = v_id;
        this.v_title = v_title;
        this.gd_id = gd_id;
        this.g_name = g_name;
        this.gd_name = gd_name;
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

    public String getV_title() {
        return v_title;
    }

    public void setV_title(String v_title) {
        this.v_title = v_title;
    }

    public int getGd_id() {
        return gd_id;
    }

    public void setGd_id(int gd_id) {
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

    @Override
    public String toString() {
        return "VodEpisodeDTO{" +
                "ve_id=" + ve_id +
                ", ve_episode_num=" + ve_episode_num +
                ", ve_contents='" + ve_contents + '\'' +
                ", ve_upload_date='" + ve_upload_date + '\'' +
                ", ve_admin='" + ve_admin + '\'' +
                ", v_id=" + v_id +
                ", v_title='" + v_title + '\'' +
                ", gd_id=" + gd_id +
                ", g_name='" + g_name + '\'' +
                ", gd_name='" + gd_name + '\'' +
                '}';
    }
}
