package com.komenta.be.model.playlist;

public class PlayListFromHistoryDTO {
    private int vh_id;
    private int pl_id;

    public PlayListFromHistoryDTO() {
    }

    public PlayListFromHistoryDTO(int vh_id, int pl_id) {
        this.vh_id = vh_id;
        this.pl_id = pl_id;
    }

    public int getVh_id() {
        return vh_id;
    }

    public void setVh_id(int vh_id) {
        this.vh_id = vh_id;
    }

    public int getPl_id() {
        return pl_id;
    }

    public void setPl_id(int pl_id) {
        this.pl_id = pl_id;
    }

    @Override
    public String toString() {
        return "PlayListFromHistoryDTO{" +
                "vh_id=" + vh_id +
                ", pl_id=" + pl_id +
                '}';
    }
}
