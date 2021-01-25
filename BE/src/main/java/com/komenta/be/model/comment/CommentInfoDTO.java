package com.komenta.be.model.comment;

public class CommentInfoDTO {
    private int c_id;
    private String c_contents;
    private String c_playtime;
    private String c_upload_time;
    private int u_id;
    private int ve_id;

    public CommentInfoDTO(String c_contents, String c_playtime, String c_upload_time, int u_id, int ve_id) {
        this.c_contents = c_contents;
        this.c_playtime = c_playtime;
        this.c_upload_time = c_upload_time;
        this.u_id = u_id;
        this.ve_id = ve_id;
    }

    public CommentInfoDTO(int c_id, String c_contents, String c_playtime, String c_upload_time, int u_id, int ve_id) {
        this.c_id = c_id;
        this.c_contents = c_contents;
        this.c_playtime = c_playtime;
        this.c_upload_time = c_upload_time;
        this.u_id = u_id;
        this.ve_id = ve_id;
    }

    public int getC_id() {
        return c_id;
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

    public int getVe_id() {
        return ve_id;
    }

    public void setVe_id(int ve_id) {
        this.ve_id = ve_id;
    }

    @Override
    public String toString() {
        return "CommentInfoTableDTO{" +
                "c_id=" + c_id +
                ", c_contents='" + c_contents + '\'' +
                ", c_playtime='" + c_playtime + '\'' +
                ", c_upload_time='" + c_upload_time + '\'' +
                ", u_id=" + u_id +
                ", ve_id=" + ve_id +
                '}';
    }
}
