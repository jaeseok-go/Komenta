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
    public List<VodEpisodeCommentDTO> getVodEpisodeComment(CommentListByVeIdDTO comment_info) {
        return sqlSession.getMapper(CommentMapper.class).getVodEpisodeComment(comment_info);
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
    public int isCommentGood(CommentGoodDTO comment_good) {
        return sqlSession.getMapper(CommentMapper.class).isCommentGood(comment_good);
    }

    @Override
    public int addLikeComment(CommentGoodDTO comment_good) {
        return sqlSession.getMapper(CommentMapper.class).addLikeComment(comment_good);
    }

    @Override
    public int cancelLikeComment(CommentGoodDTO comment_good) {
        return sqlSession.getMapper(CommentMapper.class).cancelLikeComment(comment_good);
    }

    @Override
    public int deleteMyComment(CommentInfoDTO dto) {
        return sqlSession.getMapper(CommentMapper.class).deleteComment(dto);
    }
}
