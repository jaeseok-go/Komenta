package com.komenta.be.model.vod;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VodInfoByGenreDTO {
    private int v_id;
    private String v_poster;
    private String v_title;
}
