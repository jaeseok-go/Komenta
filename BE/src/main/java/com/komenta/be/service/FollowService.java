package com.komenta.be.service;

import com.komenta.be.model.follow.FollowDTO;
import com.komenta.be.model.follow.FollowSelectDTO;
import com.komenta.be.model.follow.UnFollowDTO;

import java.util.List;

public interface FollowService {
    public List<FollowSelectDTO> getFollowingList(int u_id);

    public List<FollowSelectDTO> getUnFollowingList(int u_id);

    public List<FollowSelectDTO> getFollowerList(int u_id);

    public int followAddSub(FollowDTO follow_info);

    public int unFollowAddSub(UnFollowDTO unfollow_info);

}
