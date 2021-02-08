package com.komenta.be.model.playlist;

public class AddContentsReviewDTO {
    private int plc_id;
    private String vh_comment;

    public AddContentsReviewDTO() {
    }

    public AddContentsReviewDTO(int plc_id, String vh_comment) {
        this.plc_id = plc_id;
        this.vh_comment = vh_comment;
    }

    public int getPlc_id() {
        return plc_id;
    }

    public void setPlc_id(int plc_id) {
        this.plc_id = plc_id;
    }

    public String getVh_comment() {
        return vh_comment;
    }

    public void setVh_comment(String vh_comment) {
        this.vh_comment = vh_comment;
    }

    @Override
    public String toString() {
        return "AddContentsReviewDTO{" +
                "plc_id=" + plc_id +
                ", vh_comment='" + vh_comment + '\'' +
                '}';
    }
}
