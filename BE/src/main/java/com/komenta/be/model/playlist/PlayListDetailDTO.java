package com.komenta.be.model.playlist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlayListDetailDTO {
    private int pl_id;
    private String pl_name;
    private String pl_comment;
    private int pl_good_count;
    private int u_id;
    private String u_nickname;
    private String u_profile_pic;
    private int plc_id;
    private int v_id;
    private String v_title;
    private String v_actors;
    private String v_director;
    private String v_poster;
    private int ve_id;
    private int ve_episode_num;
    private int vh_id;
    private String vh_comment;
    private int vh_score;
    private String g_name;
    private String gd_name;
}
