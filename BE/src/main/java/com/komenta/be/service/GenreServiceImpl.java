package com.komenta.be.service;

import com.komenta.be.mapper.GenreMapper;
import com.komenta.be.mapper.MemberMapper;
import com.komenta.be.model.genre.GenreDTO;
import com.komenta.be.model.genre.GenreDetailDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService{

    @Autowired
    SqlSession sqlSession;

    @Override
    public List<GenreDTO> selectAllGenre() {
        return sqlSession.getMapper(GenreMapper.class).selectAllGenre();
    }

    @Override
    public List<GenreDetailDTO> selectAllGenreDetail(int g_id) {
        return sqlSession.getMapper(GenreMapper.class).selectAllGenreDetail(g_id);
    }
}
