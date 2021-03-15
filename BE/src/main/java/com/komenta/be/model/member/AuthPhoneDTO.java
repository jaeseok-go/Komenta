package com.komenta.be.model.member;

public class AuthPhoneDTO {

    private String auth_number;
    private String u_email;

    public AuthPhoneDTO() {
    }

    public AuthPhoneDTO(String auth_number, String u_email) {
        this.auth_number = auth_number;
        this.u_email = u_email;
    }

    public String getAuth_number() {
        return auth_number;
    }

    public void setAuth_number(String auth_number) {
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
        return "AuthPhoneDTO{" +
                "auth_number='" + auth_number + '\'' +
                ", u_email='" + u_email + '\'' +
                '}';
    }
}
