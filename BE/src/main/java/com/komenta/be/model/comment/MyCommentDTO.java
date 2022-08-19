package com.komenta.be.model.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MyCommentDTO {

    private int c_id;
    private String c_contents;
    private String c_playtime;
    private String c_upload_time;
    private int ve_id;
    private int ve_episode_num;
    private String v_title;
    private int c_good_count;
    private int re_comment_count;
}
