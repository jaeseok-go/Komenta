package com.komenta.be.model.vod;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VodUpdateTimeDTO {
    private int u_id;
    private int ve_id;
    private String vh_watching_time;
}
