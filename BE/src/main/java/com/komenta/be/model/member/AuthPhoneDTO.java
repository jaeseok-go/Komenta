package com.komenta.be.model.member;

public class AuthPhoneDTO {

    private int auth_number;
    private String u_email;

    public AuthPhoneDTO() {
    }

    public AuthPhoneDTO(int auth_number, String u_email) {
        this.auth_number = auth_number;
        this.u_email = u_email;
    }

    public int getAuth_number() {
        return auth_number;
    }

    public void setAuth_number(int auth_number) {
        this.auth_number = auth_number;
    }

    public String getU_email() {
        return u_email;
    }

    public void setU_email(String u_email) {
        this.u_email = u_email;
    }

    @Override
    public String toString() {
        return "AuthPhone{" +
                "auth_number=" + auth_number +
                ", u_email='" + u_email + '\'' +
                '}';
    }
}
