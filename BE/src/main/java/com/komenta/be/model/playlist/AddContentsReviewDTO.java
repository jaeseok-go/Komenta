package com.komenta.be.model.playlist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddContentsReviewDTO {
    private int plc_id;
    private String vh_comment;
}
