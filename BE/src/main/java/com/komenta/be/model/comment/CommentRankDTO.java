package com.komenta.be.model.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentRankDTO {
    private int rank;
    private int v_id;
    private String v_title;
    private int ve_id;
    private int ve_episode_num;
    private int c_id;
    private int c_good_count;
    private String c_contents;
    private String c_playtime;
    private int u_id;
    private String u_email;
    private String u_nickname;
    private String u_profile_pic;
}
