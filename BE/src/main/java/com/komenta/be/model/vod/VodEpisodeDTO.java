package com.komenta.be.model.vod;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VodEpisodeDTO {
    private int ve_id;
    private int ve_episode_num;
    private String ve_contents;
    private String ve_upload_date;
    private String ve_admin;
    private int v_id;
    private String v_title;
    private int gd_id;
    private String g_name;
    private String gd_name;
}
