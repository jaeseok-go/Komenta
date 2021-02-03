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
    public int followAddSub(FollowDTO follow_info) {
        return sqlSession.getMapper(FollowMapper.class).followAddSub(follow_info);
    }

    @Override
    public int unFollowAddSub(UnFollowDTO unfollow_info) {
        return sqlSession.getMapper(FollowMapper.class).unFollowAddSub(unfollow_info);
    }
}
