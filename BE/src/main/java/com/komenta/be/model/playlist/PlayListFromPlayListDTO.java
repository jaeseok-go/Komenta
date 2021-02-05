package com.komenta.be.model.playlist;

public class PlayListFromPlayListDTO {
    private int plc_id;
    private int pl_id;

    public PlayListFromPlayListDTO() {
    }

    public PlayListFromPlayListDTO(int plc_id, int pl_id) {
        this.plc_id = plc_id;
        this.pl_id = pl_id;
    }

    public int getPlc_id() {
        return plc_id;
    }

    public void setPlc_id(int plc_id) {
        this.plc_id = plc_id;
    }

    public int getPl_id() {
        return pl_id;
    }

    public void setPl_id(int pl_id) {
        this.pl_id = pl_id;
    }

    @Override
    public String toString() {
        return "PlayListFromPlayListDTO{" +
                "plc_id=" + plc_id +
                ", pl_id=" + pl_id +
                '}';
    }
}
