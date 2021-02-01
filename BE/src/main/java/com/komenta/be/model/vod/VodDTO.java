package com.komenta.be.model.vod;

public class VodDTO {
    private int v_id;
    private String v_title;
    private String v_summary;
    private String v_director;
    private String v_actors;
    private int v_age_grade;
    private String v_poster;
    private int gd_id;
    private String gd_name;
    private String g_name;

    public VodDTO(){
    }

    public VodDTO(int v_id, String v_title, String v_summary, String v_director, String v_actors, int v_age_grade, String v_poster, int gd_id, String gd_name, String g_name) {
        this.v_id = v_id;
        this.v_title = v_title;
        this.v_summary = v_summary;
        this.v_director = v_director;
        this.v_actors = v_actors;
        this.v_age_grade = v_age_grade;
        this.v_poster = v_poster;
        this.gd_id = gd_id;
        this.gd_name = gd_name;
        this.g_name = g_name;
    }

    public VodDTO(int v_id, String v_title, String v_summary, String v_director, String v_actors, int v_age_grade, String v_poster, int gd_id) {
        this.v_id = v_id;
        this.v_title = v_title;
        this.v_summary = v_summary;
        this.v_director = v_director;
        this.v_actors = v_actors;
        this.v_age_grade = v_age_grade;
        this.v_poster = v_poster;
        this.gd_id = gd_id;
    }

    public VodDTO(String v_title, String v_summary, String v_director, String v_actors, int v_age_grade, String v_poster, int gd_id) {
        this.v_title = v_title;
        this.v_summary = v_summary;
        this.v_director = v_director;
        this.v_actors = v_actors;
        this.v_age_grade = v_age_grade;
        this.v_poster = v_poster;
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

    public String getGd_name() {
        return gd_name;
    }

    public void setGd_name(String gd_name) {
        this.gd_name = gd_name;
    }

    public String getG_name() {
        return g_name;
    }

    public void setG_name(String g_name) {
        this.g_name = g_name;
    }

    @Override
    public String toString() {
        return "VodDTO{" +
                "v_id=" + v_id +
                ", v_title='" + v_title + '\'' +
                ", v_summary='" + v_summary + '\'' +
                ", v_director='" + v_director + '\'' +
                ", v_actors='" + v_actors + '\'' +
                ", v_age_grade=" + v_age_grade +
                ", v_poster='" + v_poster + '\'' +
                ", gd_id=" + gd_id +
                ", gd_name='" + gd_name + '\'' +
                ", g_name='" + g_name + '\'' +
                '}';
    }
}
