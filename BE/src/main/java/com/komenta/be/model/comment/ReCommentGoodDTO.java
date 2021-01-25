package com.komenta.be.model.comment;

public class ReCommentGoodDTO {
    private int rc_id;
    private int u_id;

    public ReCommentGoodDTO(int rc_id, int u_id) {
        this.rc_id = rc_id;
        this.u_id = u_id;
    }

    public int getRc_id() {
        return rc_id;
    }

    public void setRc_id(int rc_id) {
        this.rc_id = rc_id;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    @Override
    public String toString() {
        return "ReCommentGoodTableDTO{" +
                "rc_id=" + rc_id +
                ", u_id=" + u_id +
                '}';
    }
}
