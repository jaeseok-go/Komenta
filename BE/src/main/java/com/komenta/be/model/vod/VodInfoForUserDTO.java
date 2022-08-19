package com.komenta.be.model.vod;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VodInfoForUserDTO {
    private int v_id;
    private String v_title;
    private String g_name;
    private String gd_name;
    private String v_actors;
    private String v_director;
    private int ve_id;
    private int ve_episode_num;
}
