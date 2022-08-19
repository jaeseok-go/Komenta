package com.komenta.be.model.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReCommentDTO {
    private int rc_id;
    private String rc_contents;
    private String rc_upload_time;
    private int u_id;
    private int c_id;
}
