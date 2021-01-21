package com.komenta.be.model.comment;

public class CommentGoodTableDTO {
    private int c_id;
    private int u_id;

    public CommentGoodTableDTO(int c_id, int u_id) {
        this.c_id = c_id;
        this.u_id = u_id;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    @Override
    public String toString() {
        return "CommentGoodTableDTO{" +
                "c_id=" + c_id +
                ", u_id=" + u_id +
                '}';
    }
}
