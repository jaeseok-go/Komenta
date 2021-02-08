package com.komenta.be.model.playlist;

public class ContentDetailDTO {
    private String v_title;
    private int ve_episode_num;
    private String vh_comment;

    public ContentDetailDTO() {
    }

    public ContentDetailDTO(String v_title, int ve_episode_num, String vh_comment) {
        this.v_title = v_title;
        this.ve_episode_num = ve_episode_num;
        this.vh_comment = vh_comment;
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

    public String getVh_comment() {
        return vh_comment;
    }

    public void setVh_comment(String vh_comment) {
        this.vh_comment = vh_comment;
    }

    @Override
    public String toString() {
        return "ContentDetailDTO{" +
                "v_title='" + v_title + '\'' +
                ", ve_episode_num=" + ve_episode_num +
                ", vh_comment='" + vh_comment + '\'' +
                '}';
    }
}
