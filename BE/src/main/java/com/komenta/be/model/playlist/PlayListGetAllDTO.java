package com.komenta.be.model.playlist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlayListGetAllDTO {
    private int pl_id;
    private int u_id;
    private int plc_id;
    private int vh_id;
    private int v_id;
    private String v_poster;
    private int ve_id;
    private int ve_episode_num;
    private String v_title;
    private String v_actors;
    private String v_director;

    private int pl_good_count;
    private String vh_watching_time;
    private String vh_comment;
    private int vh_score;
    private boolean vh_good;

    private String gd_id;
    private String g_name;
    private String gd_name;

    // 없어도 될 것 같은 녀석들
    private String pl_name;
    private String pl_comment;
}
