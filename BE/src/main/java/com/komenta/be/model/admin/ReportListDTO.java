package com.komenta.be.model.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReportListDTO {
    private int r_id;
    private String r_type;
    private String r_contents;
    private int cu_id;
    private int u_id;
    private String u_email;
    private String u_pw;
    private String u_phone_number;
    private String u_nickname;
    private String u_expire_member;
    private boolean u_is_admin;
    private boolean u_is_blocked;
    private String u_profile_pic;
}
