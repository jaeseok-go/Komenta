package com.komenta.be.model.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthPhoneDTO {
    private String auth_number;
    private String u_email;
}
