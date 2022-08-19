package com.komenta.be.model.vod;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VodSearchInfoDTO {
    private int v_id;
    private String v_title;
    private int ve_episode_num;
    private int ve_id;
    private int gd_id;
}
