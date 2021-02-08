package com.komenta.be.model.vod;

public class VodHistorySetDTO {

    private int u_id;
    private int ve_id;

    public VodHistorySetDTO(){}

    public VodHistorySetDTO(int u_id, int ve_id) {
        this.u_id = u_id;
        this.ve_id = ve_id;
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
        return "VodHistorySetDTO{" +
                "u_id=" + u_id +
                ", ve_id=" + ve_id +
                '}';
    }
}
