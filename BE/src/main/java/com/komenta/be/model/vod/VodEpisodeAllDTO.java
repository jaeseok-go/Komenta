package com.komenta.be.model.vod;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VodEpisodeAllDTO {
    private int ve_id;
    private int ve_episode_num;
    private String ve_contents;
    private String ve_admin;
    private String ve_upload_date;
    private int v_id;
    private String v_title;
    private String v_summary;
    private String v_director;
    private String v_actors;
    private int v_age_grade;
    private String v_poster;
    private int gd_id;
    private String g_name;
    private String gd_name;
}
