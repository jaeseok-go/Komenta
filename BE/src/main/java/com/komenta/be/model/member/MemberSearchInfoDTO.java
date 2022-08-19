package com.komenta.be.model.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberSearchInfoDTO {
    private int u_id;
    private String u_nickname;
    private String u_email;
}
