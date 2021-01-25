package com.komenta.be.model.follow;

public class FollowDTO {
    private int f_id;
    private int u_id;

    public FollowDTO(int f_id, int u_id) {
        this.f_id = f_id;
        this.u_id = u_id;
    }

    public int getF_id() {
        return f_id;
    }

    public void setF_id(int f_id) {
        this.f_id = f_id;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    @Override
    public String toString() {
        return "FollowTableDTO{" +
                "f_id=" + f_id +
                ", u_id=" + u_id +
                '}';
    }
}
