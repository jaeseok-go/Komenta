package com.komenta.be.model.playlist;

import java.util.List;

public class PlaytListBestDTO {
    private int plb_id;
    private int plb_cnt;
    private int plb_rank;
    private List<PlayListDetailDTO> pldetail;
    public PlaytListBestDTO() {

    }

    public PlaytListBestDTO(int plb_id, int plb_cnt, int plb_rank, List<PlayListDetailDTO> pldetail) {
        this.plb_id = plb_id;
        this.plb_cnt = plb_cnt;
        this.plb_rank = plb_rank;
        this.pldetail = pldetail;
    }

    public PlaytListBestDTO(int plb_id, int plb_cnt, int plb_rank) {
        this.plb_id = plb_id;
        this.plb_cnt = plb_cnt;
        this.plb_rank = plb_rank;
    }

    public int getPlb_id() {
        return plb_id;
    }

    public void setPlb_id(int plb_id) {
        this.plb_id = plb_id;
    }

    public int getPlb_cnt() {
        return plb_cnt;
    }

    public void setPlb_cnt(int plb_cnt) {
        this.plb_cnt = plb_cnt;
    }

    public int getPlb_rank() {
        return plb_rank;
    }

    public void setPlb_rank(int plb_rank) {
        this.plb_rank = plb_rank;
    }

    public List<PlayListDetailDTO> getPldetail() {
        return pldetail;
    }

    public void setPldetail(List<PlayListDetailDTO> pldetail) {
        this.pldetail = pldetail;
    }

    @Override
    public String toString() {
        return "PlaytListBestDTO{" +
                "plb_id=" + plb_id +
                ", plb_cnt=" + plb_cnt +
                ", plb_rank=" + plb_rank +
                ", pldetail=" + pldetail +
                '}';
    }
}
