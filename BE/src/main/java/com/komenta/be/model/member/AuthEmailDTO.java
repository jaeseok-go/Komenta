package com.komenta.be.model.member;

public class AuthEmailDTO {

    private int auth_number;
    private String u_password;

    public AuthEmailDTO() {
    }

    public AuthEmailDTO(int auth_number, String u_password) {
        this.auth_number = auth_number;
        this.u_password = u_password;
    }

    public int getAuth_number() {
        return auth_number;
    }

    public void setAuth_number(int auth_number) {
        this.auth_number = auth_number;
    }

    public String getU_password() {
        return u_password;
    }

    public void setU_password(String u_password) {
        this.u_password = u_password;
    }

    @Override
    public String toString() {
        return "AuthEmailDTO{" +
                "auth_number=" + auth_number +
                ", u_password='" + u_password + '\'' +
                '}';
    }
}
