package com.komenta.be.model.playlist;

public class PlayListGoodTableDTO {
    private int pl_id;
    private int u_id;

    public PlayListGoodTableDTO(int pl_id, int u_id) {
        this.pl_id = pl_id;
        this.u_id = u_id;
    }

    public int getPl_id() {
        return pl_id;
    }

    public void setPl_id(int pl_id) {
        this.pl_id = pl_id;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    @Override
    public String toString() {
        return "PlayListGoodTableDTO{" +
                "pl_id=" + pl_id +
                ", u_id=" + u_id +
                '}';
    }
}
