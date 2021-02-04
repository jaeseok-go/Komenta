package com.komenta.be.model.comment;

public class MyCommentDTO {

    private int c_id;
    private String c_contents;
    private String c_playtime;
    private String c_upload_time;
    private int ve_id;
    private int ve_episode_num;
    private String v_title;
    private int c_good_count;
    private int re_comment_count;

    public MyCommentDTO() {
    }

    public MyCommentDTO(int c_id, String c_contents, String c_playtime, String c_upload_time, int ve_id, int ve_episode_num, String v_title, int c_good_count, int re_comment_count) {
        this.c_id = c_id;
        this.c_contents = c_contents;
        this.c_playtime = c_playtime;
        this.c_upload_time = c_upload_time;
        this.ve_id = ve_id;
        this.ve_episode_num = ve_episode_num;
        this.v_title = v_title;
        this.c_good_count = c_good_count;
        this.re_comment_count = re_comment_count;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
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

    public String getC_upload_time() {
        return c_upload_time;
    }

    public void setC_upload_time(String c_upload_time) {
        this.c_upload_time = c_upload_time;
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

    public int getC_good_count() {
        return c_good_count;
    }

    public void setC_good_count(int c_good_count) {
        this.c_good_count = c_good_count;
    }

    public int getRe_comment_count() {
        return re_comment_count;
    }

    public void setRe_comment_count(int re_comment_count) {
        this.re_comment_count = re_comment_count;
    }

    @Override
    public String toString() {
        return "MyCommentDTO{" +
                "c_id=" + c_id +
                ", c_contents='" + c_contents + '\'' +
                ", c_playtime='" + c_playtime + '\'' +
                ", c_upload_time='" + c_upload_time + '\'' +
                ", ve_id=" + ve_id +
                ", ve_episode_num=" + ve_episode_num +
                ", v_title='" + v_title + '\'' +
                ", c_good_count=" + c_good_count +
                ", re_comment_count=" + re_comment_count +
                '}';
    }
}
