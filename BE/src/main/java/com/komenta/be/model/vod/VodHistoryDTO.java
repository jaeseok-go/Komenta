package com.komenta.be.model.vod;

public class VodHistoryDTO {
    private int vh_id;
    private String vh_watching_time;
    private String vh_comment;
    private int vh_score;
    private boolean vh_good;
    private int u_id;
    private int ve_id;

    public VodHistoryDTO(String vh_watching_time, String vh_comment, int vh_score, boolean vh_good, int u_id, int ve_id) {
        this.vh_watching_time = vh_watching_time;
        this.vh_comment = vh_comment;
        this.vh_score = vh_score;
        this.vh_good = vh_good;
        this.u_id = u_id;
        this.ve_id = ve_id;
    }

    public VodHistoryDTO(int vh_id, String vh_watching_time, String vh_comment, int vh_score, boolean vh_good, int u_id, int ve_id) {
        this.vh_id = vh_id;
        this.vh_watching_time = vh_watching_time;
        this.vh_comment = vh_comment;
        this.vh_score = vh_score;
        this.vh_good = vh_good;
        this.u_id = u_id;
        this.ve_id = ve_id;
    }

    public int getVh_id() {
        return vh_id;
    }

    public String getVh_watching_time() {
        return vh_watching_time;
    }

    public void setVh_watching_time(String vh_watching_time) {
        this.vh_watching_time = vh_watching_time;
    }

    public String getVh_comment() {
        return vh_comment;
    }

    public void setVh_comment(String vh_comment) {
        this.vh_comment = vh_comment;
    }

    public int getVh_score() {
        return vh_score;
    }

    public void setVh_score(int vh_score) {
        this.vh_score = vh_score;
    }

    public boolean isVh_good() {
        return vh_good;
    }

    public void setVh_good(boolean vh_good) {
        this.vh_good = vh_good;
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
        return "VodHistoryTableDTO{" +
                "vh_id=" + vh_id +
                ", vh_watching_time='" + vh_watching_time + '\'' +
                ", vh_comment='" + vh_comment + '\'' +
                ", vh_score=" + vh_score +
                ", vh_good=" + vh_good +
                ", u_id=" + u_id +
                ", ve_id=" + ve_id +
                '}';
    }
}
