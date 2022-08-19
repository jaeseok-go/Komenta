package com.komenta.be.model.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReportDTO {
    private int r_id;
    private String r_type;
    private String r_contents;
    private int u_id;
    private int c_id;
    private int rc_id;
}
