package com.komenta.be.mapper;

import com.komenta.be.model.follow.FollowDTO;
import com.komenta.be.model.follow.FollowSelectDTO;
import com.komenta.be.model.follow.UnFollowDTO;

import java.util.List;

public interface FollowMapper {

    public List<FollowSelectDTO> getFollowingList(int u_id);

    public List<FollowSelectDTO> getUnFollowingList(int u_id);

    public List<FollowSelectDTO> getFollowerList(int u_id);



    public List<FollowDTO> isFollower(FollowDTO follow_info);

    public int followAdd(FollowDTO follow_info);

    public int followSub(FollowDTO follow_info);



    public List<UnFollowDTO> isUnFollower(UnFollowDTO unfollow_info);

    public int unFollowAdd(UnFollowDTO unfollow_info);

    public int unFollowSub(UnFollowDTO unfollow_info);
}
