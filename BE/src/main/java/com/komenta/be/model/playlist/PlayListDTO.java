package com.komenta.be.model.playlist;

public class PlayListDTO {
    private int pl_id;
    private String pl_name;
    private int u_id;

    public PlayListDTO(String pl_name, int u_id) {
        this.pl_name = pl_name;
        this.u_id = u_id;
    }

    public PlayListDTO(int pl_id, String pl_name, int u_id) {
        this.pl_id = pl_id;
        this.pl_name = pl_name;
        this.u_id = u_id;
    }

    public int getPl_id() {
        return pl_id;
    }

    public String getPl_name() {
        return pl_name;
    }

    public void setPl_name(String pl_name) {
        this.pl_name = pl_name;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    @Override
    public String toString() {
        return "PlayListTableDTO{" +
                "pl_id=" + pl_id +
                ", pl_name='" + pl_name + '\'' +
                ", u_id=" + u_id +
                '}';
    }
}
