package com.komenta.be.model.playlist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlayListDTO {
    private int pl_id;
    private String pl_name;
    private String pl_comment;
    private int u_id;
}
