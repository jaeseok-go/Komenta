package com.komenta.be.model.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentListByVeIdDTO {
    private int ve_id;
    private int u_id;
}
