package com.komenta.be.model.vod;

public class VodInfoByGenreDTO {
    private int v_id;
    private String v_poster;
    private String v_title;

    public VodInfoByGenreDTO(){}

    public VodInfoByGenreDTO(int v_id, String v_poster, String v_title) {
        this.v_id = v_id;
        this.v_poster = v_poster;
        this.v_title = v_title;
    }

    public int getV_id() {
        return v_id;
    }

    public void setV_id(int v_id) {
        this.v_id = v_id;
    }

    public String getV_poster() {
        return v_poster;
    }

    public void setV_poster(String v_poster) {
        this.v_poster = v_poster;
    }

    public String getV_title() {
        return v_title;
    }

    public void setV_title(String v_title) {
        this.v_title = v_title;
    }

    @Override
    public String toString() {
        return "VodInfoByGenreDTO{" +
                "v_id=" + v_id +
                ", v_poster='" + v_poster + '\'' +
                ", v_title='" + v_title + '\'' +
                '}';
    }
}
