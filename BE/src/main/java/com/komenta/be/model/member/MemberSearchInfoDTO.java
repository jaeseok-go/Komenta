package com.komenta.be.model.member;

public class MemberSearchInfoDTO {
    private int u_id;
    private String u_nickname;
    private String u_email;

    public MemberSearchInfoDTO() {
    }

    public MemberSearchInfoDTO(int u_id, String u_nickname, String u_email) {
        this.u_id = u_id;
        this.u_nickname = u_nickname;
        this.u_email = u_email;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public String getU_nickname() {
        return u_nickname;
    }

    public void setU_nickname(String u_nickname) {
        this.u_nickname = u_nickname;
    }

    public String getU_email() {
        return u_email;
    }

    public void setU_email(String u_email) {
        this.u_email = u_email;
    }


}
