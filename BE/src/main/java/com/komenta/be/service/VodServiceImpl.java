package com.komenta.be.service;

import com.komenta.be.mapper.VodMapper;
import com.komenta.be.model.vod.*;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VodServiceImpl implements VodService{

    @Autowired
    SqlSession sqlSession;

    @Override
    public List<VodHistoryDTO> selectHistoryById(int u_id) {

        return sqlSession.getMapper(VodMapper.class).selectHistoryById(u_id);
    }

    @Override
    public VodEpisodeAllDTO selectEpisodeById(int ve_id) {
        return sqlSession.getMapper(VodMapper.class).selectEpisodeById(ve_id);
    }

    @Override
    public List<VodEpisodeDTO> getVodListRecent() {
        return sqlSession.getMapper(VodMapper.class).getVodListRecent();
    }

    @Override
    public List<VodEpisodeDTO> getVodListComment() {
        return sqlSession.getMapper(VodMapper.class).getVodListComment();
    }

    @Override
    public List<VodEpisodeDTO> getVodListWatching() { return sqlSession.getMapper(VodMapper.class).getVodListWatching(); }

    @Override
    public List<VodInfoForUserDTO> getVodInfoForUser(int v_id) { return sqlSession.getMapper(VodMapper.class).getVodInfoForUser(v_id); }

    @Override
    public List<VodInfoByGenreDTO> getVodListGenre(int g_id) {
        return sqlSession.getMapper(VodMapper.class).getVodListGenre(g_id);
    }

    @Override
    public List<VodInfoByGenreDTO> getVodListGenreDetail(int gd_id) {
        return sqlSession.getMapper(VodMapper.class).getVodListGenreDetail(gd_id);
    }

    @Override
    public List<VodSearchInfoDTO> getAllVod() {
        return sqlSession.getMapper(VodMapper.class).getAllVod();
    }

    @Override
    public int getVodHistoryByUAndVe(VodHistorySetDTO history) {
        return sqlSession.getMapper(VodMapper.class).getVodHistoryByUAndVe(history);
    }

    @Override
    public int insertVodHistory(VodHistorySetDTO history) {
        return sqlSession.getMapper(VodMapper.class).insertVodHistory(history);
    }

    @Override
    public int updateTime(VodUpdateTimeDTO history) {
        return sqlSession.getMapper(VodMapper.class).updateTime(history);
    }
}
