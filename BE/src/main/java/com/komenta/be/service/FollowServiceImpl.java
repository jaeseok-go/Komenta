package com.komenta.be.service;

import com.komenta.be.mapper.FollowMapper;
import com.komenta.be.mapper.GenreMapper;
import com.komenta.be.model.follow.FollowDTO;
import com.komenta.be.model.follow.FollowSelectDTO;
import com.komenta.be.model.follow.UnFollowDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowServiceImpl implements FollowService{

    @Autowired
    SqlSession sqlSession;

    @Override
    public List<FollowSelectDTO> getFollowingList(int u_id) {
        return sqlSession.getMapper(FollowMapper.class).getFollowingList(u_id);
    }

    @Override
    public List<FollowSelectDTO> getUnFollowingList(int u_id) {
        return sqlSession.getMapper(FollowMapper.class).getUnFollowingList(u_id);
    }

    @Override
    public List<FollowSelectDTO> getFollowerList(int u_id) {
        return sqlSession.getMapper(FollowMapper.class).getFollowerList(u_id);
    }

    @Override
    public List<FollowDTO> isFollower(FollowDTO follow_info) {
        return sqlSession.getMapper(FollowMapper.class).isFollower(follow_info);
    }

    @Override
    public int followAdd(FollowDTO follow_info) {
        return sqlSession.getMapper(FollowMapper.class).followAdd(follow_info);
    }

    @Override
    public int followSub(FollowDTO follow_info) {
        return sqlSession.getMapper(FollowMapper.class).followSub(follow_info);
    }

    @Override
    public List<UnFollowDTO> isUnFollower(UnFollowDTO unfollow_info) {
        return sqlSession.getMapper(FollowMapper.class).isUnFollower(unfollow_info);
    }

    @Override
    public int unFollowAdd(UnFollowDTO unfollow_info) {
        return sqlSession.getMapper(FollowMapper.class).unFollowAdd(unfollow_info);
    }

    @Override
    public int unFollowSub(UnFollowDTO unfollow_info) {
        return sqlSession.getMapper(FollowMapper.class).unFollowSub(unfollow_info);
    }

}
