package com.komenta.be.model.follow;

public class BlockedVodTableDTO {
    private int u_id;
    private int v_id;

    public BlockedVodTableDTO(int u_id, int v_id) {
        this.u_id = u_id;
        this.v_id = v_id;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public int getV_id() {
        return v_id;
    }

    public void setV_id(int v_id) {
        this.v_id = v_id;
    }

    @Override
    public String toString() {
        return "BlockedVodTableDTO{" +
                "u_id=" + u_id +
                ", v_id=" + v_id +
                '}';
    }
}
