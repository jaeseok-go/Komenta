package com.komenta.be.model.genre;

public class GenreDetailDTO {
    private int gd_id;
    private String gd_name;
    private int g_id;

    private GenreDTO gdto;

    public GenreDetailDTO(String gd_name, int g_id) {
        this.gd_name = gd_name;
        this.g_id = g_id;
    }

    public GenreDetailDTO(int gd_id, String gd_name, int g_id) {
        this.gd_id = gd_id;
        this.gd_name = gd_name;
        this.g_id = g_id;
    }

    public int getGd_id() {
        return gd_id;
    }

    public String getGd_name() {
        return gd_name;
    }

    public void setGd_name(String gd_name) {
        this.gd_name = gd_name;
    }

    public int getG_id() {
        return g_id;
    }

    public void setG_id(int g_id) {
        this.g_id = g_id;
    }

    @Override
    public String toString() {
        return "GenreDetailTableDTO{" +
                "gd_id=" + gd_id +
                ", gd_name='" + gd_name + '\'' +
                ", g_id=" + g_id +
                '}';
    }
}
