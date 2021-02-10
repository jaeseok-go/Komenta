package com.komenta.be.service;

import com.komenta.be.mapper.CommentMapper;
import com.komenta.be.mapper.GenreMapper;
import com.komenta.be.model.comment.*;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    SqlSession sqlSession;


    @Override
    public int insertComment(CommentInfoDTO comment_info) {
        return sqlSession.getMapper(CommentMapper.class).insertComment(comment_info);
    }

    @Override
    public List<VodEpisodeCommentDTO> getVodEpisodeComment(int ve_id) {
        return sqlSession.getMapper(CommentMapper.class).getVodEpisodeComment(ve_id);
    }

    @Override
    public List<CommentRankDTO> getCommentRankList() {
        return sqlSession.getMapper(CommentMapper.class).getCommentRankList();
    }

    @Override
    public List<MyCommentDTO> getMyComment(int u_id) {
        return sqlSession.getMapper(CommentMapper.class).getMyComment(u_id);
    }

    @Override
    public int addLikeComment(CommentGoodDTO dto) {
        return sqlSession.getMapper(CommentMapper.class).addLikeComment(dto);
    }

    @Override
    public int cancelLikeComment(CommentGoodDTO dto) {
        return sqlSession.getMapper(CommentMapper.class).cancelLikeComment(dto);
    }
}
