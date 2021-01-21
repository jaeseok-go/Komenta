package com.komenta.be.model.comment;

public class ReCommentTableDTO {
    private int rc_id;
    private String rc_contents;
    private String rc_upload_time;
    private int u_id;
    private int c_id;

    public ReCommentTableDTO(String rc_contents, String rc_upload_time, int u_id, int c_id) {
        this.rc_contents = rc_contents;
        this.rc_upload_time = rc_upload_time;
        this.u_id = u_id;
        this.c_id = c_id;
    }

    public ReCommentTableDTO(int rc_id, String rc_contents, String rc_upload_time, int u_id, int c_id) {
        this.rc_id = rc_id;
        this.rc_contents = rc_contents;
        this.rc_upload_time = rc_upload_time;
        this.u_id = u_id;
        this.c_id = c_id;
    }

    public int getRc_id() {
        return rc_id;
    }

    public String getRc_contents() {
        return rc_contents;
    }

    public void setRc_contents(String rc_contents) {
        this.rc_contents = rc_contents;
    }

    public String getRc_upload_time() {
        return rc_upload_time;
    }

    public void setRc_upload_time(String rc_upload_time) {
        this.rc_upload_time = rc_upload_time;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    @Override
    public String toString() {
        return "ReCommentTableDTO{" +
                "rc_id=" + rc_id +
                ", rc_contents='" + rc_contents + '\'' +
                ", rc_upload_time='" + rc_upload_time + '\'' +
                ", u_id=" + u_id +
                ", c_id=" + c_id +
                '}';
    }
}
