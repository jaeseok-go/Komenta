package com.komenta.be.model.follow;

public class UnFollowTableDTO {
    private int uf_id;
    private int u_id;

    public UnFollowTableDTO(int uf_id, int u_id) {
        this.uf_id = uf_id;
        this.u_id = u_id;
    }

    public int getUf_id() {
        return uf_id;
    }

    public void setUf_id(int uf_id) {
        this.uf_id = uf_id;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    @Override
    public String toString() {
        return "UnFollowTableDTO{" +
                "uf_id=" + uf_id +
                ", u_id=" + u_id +
                '}';
    }
}
