package com.komenta.be.model.comment;

public class VodEpisodeCommentDTO {

    private int c_id;
    private String c_contents;
    private String c_playtime;
    private String c_upload_time;
    private int u_id;
    private String u_nickname;
    private int comment_good_count;

    public VodEpisodeCommentDTO() {
    }

    public VodEpisodeCommentDTO(int c_id, String c_contents, String c_playtime, String c_upload_time, int u_id, String u_nickname, int comment_good_count) {
        this.c_id = c_id;
        this.c_contents = c_contents;
        this.c_playtime = c_playtime;
        this.c_upload_time = c_upload_time;
        this.u_id = u_id;
        this.u_nickname = u_nickname;
        this.comment_good_count = comment_good_count;
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

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public String getU_nickname() {
        return u_nickname;
    }

    public void setU_nickname(String u_nickname) {
        this.u_nickname = u_nickname;
    }

    public int getComment_good_count() {
        return comment_good_count;
    }

    public void setComment_good_count(int comment_good_count) {
        this.comment_good_count = comment_good_count;
    }

    @Override
    public String toString() {
        return "VodEpisodeCommentDTO{" +
                "c_id=" + c_id +
                ", c_contents='" + c_contents + '\'' +
                ", c_playtime='" + c_playtime + '\'' +
                ", c_upload_time='" + c_upload_time + '\'' +
                ", u_id=" + u_id +
                ", u_nickname='" + u_nickname + '\'' +
                ", comment_good_count=" + comment_good_count +
                '}';
    }
}
