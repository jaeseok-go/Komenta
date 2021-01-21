package com.komenta.be.model.genre;

public class GenreTableDTO {
    private int g_id;
    private String g_name;

    public GenreTableDTO(String g_name) {
        this.g_name = g_name;
    }

    public GenreTableDTO(int g_id, String g_name) {
        this.g_id = g_id;
        this.g_name = g_name;
    }

    public int getG_id() {
        return g_id;
    }

    public String getG_name() {
        return g_name;
    }

    public void setG_name(String g_name) {
        this.g_name = g_name;
    }

    @Override
    public String toString() {
        return "GenreTableDTO{" +
                "g_id=" + g_id +
                ", g_name='" + g_name + '\'' +
                '}';
    }
}
