package com.komenta.be.model.playlist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContentDetailDTO {
    private String v_title;
    private int ve_episode_num;
    private String vh_comment;
}
