package com.komenta.be.model.vod;

public class VodEpisodeAllDTO {

    private int ve_id;
    private int ve_episode_num;
    private String ve_contents;
    private String ve_admin;
    private String ve_upload_date;
    private int v_id;
    private String v_title;
    private String v_summary;
    private String v_director;
    private String v_actors;
    private int v_age_grade;
    private String v_poster;
    private int gd_id;
    private String g_name;
    private String gd_name;

    public VodEpisodeAllDTO() {
    }

    public VodEpisodeAllDTO(int ve_id, int ve_episode_num, String ve_contents, String ve_admin, String ve_upload_date,
                            int v_id, String v_title, String v_summary, String v_director, String v_actors, int v_age_grade,
                            String v_poster, int gd_id, String g_name, String gd_name) {
        this.ve_id = ve_id;
        this.ve_episode_num = ve_episode_num;
        this.ve_contents = ve_contents;
        this.ve_admin = ve_admin;
        this.ve_upload_date = ve_upload_date;
        this.v_id = v_id;
        this.v_title = v_title;
        this.v_summary = v_summary;
        this.v_director = v_director;
        this.v_actors = v_actors;
        this.v_age_grade = v_age_grade;
        this.v_poster = v_poster;
        this.gd_id = gd_id;
        this.g_name = g_name;
        this.gd_name = gd_name;
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

    public String getVe_contents() {
        return ve_contents;
    }

    public void setVe_contents(String ve_contents) {
        this.ve_contents = ve_contents;
    }

    public String getVe_admin() {
        return ve_admin;
    }

    public void setVe_admin(String ve_admin) {
        this.ve_admin = ve_admin;
    }

    public String getVe_upload_date() {
        return ve_upload_date;
    }

    public void setVe_upload_date(String ve_upload_date) {
        this.ve_upload_date = ve_upload_date;
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

    public String getV_summary() {
        return v_summary;
    }

    public void setV_summary(String v_summary) {
        this.v_summary = v_summary;
    }

    public String getV_director() {
        return v_director;
    }

    public void setV_director(String v_director) {
        this.v_director = v_director;
    }

    public String getV_actors() {
        return v_actors;
    }

    public void setV_actors(String v_actors) {
        this.v_actors = v_actors;
    }

    public int getV_age_grade() {
        return v_age_grade;
    }

    public void setV_age_grade(int v_age_grade) {
        this.v_age_grade = v_age_grade;
    }

    public String getV_poster() {
        return v_poster;
    }

    public void setV_poster(String v_poster) {
        this.v_poster = v_poster;
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
        return "VodEpisodeAllDTO{" +
                "ve_id=" + ve_id +
                ", ve_episode_num=" + ve_episode_num +
                ", ve_contents='" + ve_contents + '\'' +
                ", ve_admin='" + ve_admin + '\'' +
                ", ve_upload_date='" + ve_upload_date + '\'' +
                ", v_id=" + v_id +
                ", v_title='" + v_title + '\'' +
                ", v_summary='" + v_summary + '\'' +
                ", v_director='" + v_director + '\'' +
                ", v_actors='" + v_actors + '\'' +
                ", v_age_grade=" + v_age_grade +
                ", v_poster='" + v_poster + '\'' +
                ", gd_id=" + gd_id +
                ", g_name='" + g_name + '\'' +
                ", gd_name='" + gd_name + '\'' +
                '}';
    }
}
