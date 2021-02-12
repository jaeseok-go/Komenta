package com.komenta.be.model.comment;

public class CommentListByVeIdDTO {
    private int ve_id;
    private int u_id;

    public CommentListByVeIdDTO(){}

    public CommentListByVeIdDTO(int ve_id, int u_id) {
        this.ve_id = ve_id;
        this.u_id = u_id;
    }

    public int getVe_id() {
        return ve_id;
    }

    public void setVe_id(int ve_id) {
        this.ve_id = ve_id;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    @Override
    public String toString() {
        return "CommentListByVeIdDTO{" +
                "ve_id=" + ve_id +
                ", u_id=" + u_id +
                '}';
    }
}
