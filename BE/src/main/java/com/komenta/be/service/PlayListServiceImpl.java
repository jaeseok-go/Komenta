package com.komenta.be.service;

import com.komenta.be.mapper.PlayListMapper;
import com.komenta.be.model.playlist.PlayListDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayListServiceImpl implements PlayListService{
    @Autowired
    SqlSession sqlSession;

    public int createPlayList(PlayListDTO dto){
        return sqlSession.getMapper(PlayListMapper.class).createPlayList(dto);
    }

    @Override
    public List<PlayListDTO> getPlayListById(int u_id) {
        sqlSession.getMapper(PlayListMapper.class).getPlayListById(u_id);
        return null;
    }
}
