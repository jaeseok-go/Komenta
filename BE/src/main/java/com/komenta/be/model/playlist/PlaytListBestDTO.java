package com.komenta.be.model.playlist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlaytListBestDTO {
    private int plb_id;
    private int plb_cnt;
    private int plb_rank;
    private List<PlayListDetailDTO> pldetail;
}
