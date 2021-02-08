package com.komenta.be.model.vod;

public class VodUpdateTimeDTO {

    private int u_id;
    private int ve_id;
    private String vh_watching_time;

    public VodUpdateTimeDTO(){}

    public VodUpdateTimeDTO(int u_id, int ve_id, String vh_watching_time) {
        this.u_id = u_id;
        this.ve_id = ve_id;
        this.vh_watching_time = vh_watching_time;
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

    public String getVh_watching_time() {
        return vh_watching_time;
    }

    public void setVh_watching_time(String vh_watching_time) {
        this.vh_watching_time = vh_watching_time;
    }

    @Override
    public String toString() {
        return "VodUpdateTimeDTO{" +
                "u_id=" + u_id +
                ", ve_id=" + ve_id +
                ", vh_watching_time='" + vh_watching_time + '\'' +
                '}';
    }
}
