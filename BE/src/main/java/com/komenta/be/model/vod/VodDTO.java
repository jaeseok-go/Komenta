package com.komenta.be.model.vod;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VodDTO {
    private int v_id;
    private String v_title;
    private String v_summary;
    private String v_director;
    private String v_actors;
    private int v_age_grade;
    private String v_poster;
    private int gd_id;
    private String gd_name;
    private String g_name;
}
