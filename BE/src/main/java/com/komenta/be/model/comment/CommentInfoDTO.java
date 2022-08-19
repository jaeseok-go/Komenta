package com.komenta.be.model.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentInfoDTO {
    private int c_id;
    private String c_contents;
    private String c_playtime;
    private String c_upload_time;
    private int u_id;
    private int ve_id;
}
