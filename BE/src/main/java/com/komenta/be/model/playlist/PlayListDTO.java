package com.komenta.be.model.playlist;

public class PlayListDTO {
    private int pl_id;
    private String pl_name;
    private String pl_comment;
    private int u_id;

    public PlayListDTO() {
    }

    public PlayListDTO(String pl_name, String pl_comment) {
        this.pl_name = pl_name;
        this.pl_comment = pl_comment;
    }

    public PlayListDTO(int pl_id, String pl_name, String pl_comment) {
        this.pl_id = pl_id;
        this.pl_name = pl_name;
        this.pl_comment = pl_comment;
    }

    public PlayListDTO(int pl_id, String pl_name, String pl_comment, int u_id) {
        this.pl_id = pl_id;
        this.pl_name = pl_name;
        this.pl_comment = pl_comment;
        this.u_id = u_id;
    }

    public void setPl_id(int pl_id) {
        this.pl_id = pl_id;
    }

    public void setPl_comment(String pl_comment) {
        this.pl_comment = pl_comment;
    }

    public String getPl_comment() {
        return pl_comment;
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
