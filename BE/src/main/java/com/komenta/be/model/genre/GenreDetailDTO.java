package com.komenta.be.model.genre;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GenreDetailDTO {
    private int gd_id;
    private String gd_name;
    private int g_id;

    private GenreDTO gdto;
}
