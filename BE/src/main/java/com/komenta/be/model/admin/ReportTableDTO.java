package com.komenta.be.model.admin;

public class ReportTableDTO {
    private int r_id;
    private String r_type;
    private String r_contents;
    private int u_id;
    private int c_id;
    private int rc_id;

    public ReportTableDTO(String r_type, String r_contents, int u_id, int c_id, int rc_id) {
        this.r_type = r_type;
        this.r_contents = r_contents;
        this.u_id = u_id;
        this.c_id = c_id;
        this.rc_id = rc_id;
    }

    public ReportTableDTO(int r_id, String r_type, String r_contents, int u_id, int c_id, int rc_id) {
        this.r_id = r_id;
        this.r_type = r_type;
        this.r_contents = r_contents;
        this.u_id = u_id;
        this.c_id = c_id;
        this.rc_id = rc_id;
    }

    public int getR_id() {
        return r_id;
    }

    public String getR_type() {
        return r_type;
    }

    public void setR_type(String r_type) {
        this.r_type = r_type;
    }

    public String getR_contents() {
        return r_contents;
    }

    public void setR_contents(String r_contents) {
        this.r_contents = r_contents;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public int getRc_id() {
        return rc_id;
    }

    public void setRc_id(int rc_id) {
        this.rc_id = rc_id;
    }

    @Override
    public String toString() {
        return "ReportTableDTO{" +
                "r_id=" + r_id +
                ", r_type='" + r_type + '\'' +
                ", r_contents='" + r_contents + '\'' +
                ", u_id=" + u_id +
                ", c_id=" + c_id +
                ", rc_id=" + rc_id +
                '}';
    }
}
