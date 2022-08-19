package com.komenta.be.model.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberDTO{
    private int u_id;
    private String u_email;
    private String u_pw;
    private String u_phone_number;
    private String u_nickname;
    private String u_expire_member;
    private boolean u_is_blocked;
    private boolean u_is_admin;
    private String u_profile_pic;
}