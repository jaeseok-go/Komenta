package com.komenta.be.model.vod;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VodHistoryDTO {
    private int vh_id;
    private String vh_watching_time;
    private String vh_comment;
    private int vh_score;
    private boolean vh_good;
    private int u_id;
    private int ve_id;
}
