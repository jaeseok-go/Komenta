package com.komenta.be.service;

import com.komenta.be.mapper.VodMapper;
import com.komenta.be.model.vod.VodEpisodeAllDTO;
import com.komenta.be.model.vod.VodHistoryDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VodServiceImpl implements VodService{
    @Autowired
    SqlSession sqlSession;
    @Override
    public VodHistoryDTO selectHistoryById(int u_id) {
        return sqlSession.getMapper(VodMapper.class).selectHistoryById(u_id);
    }

    @Override
    public VodEpisodeAllDTO selectEpisodeById(int ve_id) {
        return sqlSession.getMapper(VodMapper.class).selectEpisodeById(ve_id);
    }
}
