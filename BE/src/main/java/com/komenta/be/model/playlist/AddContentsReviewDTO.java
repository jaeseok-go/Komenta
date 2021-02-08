package com.komenta.be.model.playlist;

public class AddContentsReviewDTO {
    private int plc_id;
    private String plc_review;

    public AddContentsReviewDTO(){}

    public AddContentsReviewDTO(int plc_id, String plc_review) {
        this.plc_id = plc_id;
        this.plc_review = plc_review;
    }

    public int getPlc_id() {
        return plc_id;
    }

    public void setPlc_id(int plc_id) {
        this.plc_id = plc_id;
    }

    public String getPlc_review() {
        return plc_review;
    }

    public void setPlc_review(String plc_review) {
        this.plc_review = plc_review;
    }

    @Override
    public String toString() {
        return "AddContentsReviewDTO{" +
                "plc_id=" + plc_id +
                ", plc_review='" + plc_review + '\'' +
                '}';
    }
}
