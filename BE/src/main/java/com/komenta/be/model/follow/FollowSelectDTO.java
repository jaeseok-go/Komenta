package com.komenta.be.model.follow;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FollowSelectDTO {
    private int f_id;
    private String u_nickname;
    private String u_profile_pic;
}
