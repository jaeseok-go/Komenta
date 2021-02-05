package com.komenta.be.service;

import com.komenta.be.mapper.PlayListMapper;
import com.komenta.be.model.playlist.PlayListDTO;
import com.komenta.be.model.playlist.PlayListFromHistoryDTO;
import com.komenta.be.model.playlist.PlayListGetAllDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayListServiceImpl implements PlayListService{
    @Autowired
    SqlSession sqlSession;

    @Override
    public int createPlayList(PlayListDTO dto){
        return sqlSession.getMapper(PlayListMapper.class).createPlayList(dto);
    }

    @Override
    public int playlist_info_modify(PlayListDTO dto) {
        return sqlSession.getMapper(PlayListMapper.class).playlist_info_modify(dto);
    }

    @Override
    public int playlist_delete(int pl_id) {
        return sqlSession.getMapper(PlayListMapper.class).playlist_delete(pl_id);
    }

    @Override
    public List<PlayListGetAllDTO> playlist_info(int pl_id) {
        return sqlSession.getMapper(PlayListMapper.class).playlist_info(pl_id);
    }

    @Override
    public List<Integer> select_favorite_pl_id(int u_id) {
        return sqlSession.getMapper(PlayListMapper.class).select_favorite_pl_id(u_id);
    }

    @Override
    public List<Integer> select_regist_pl_id(int u_id) {
        return sqlSession.getMapper(PlayListMapper.class).select_regist_pl_id(u_id);
    }

    @Override
    public int moveHistoryToPlaylist(PlayListFromHistoryDTO move_info) {
        return sqlSession.getMapper(PlayListMapper.class).moveHistoryToPlaylist(move_info);
    }

}
