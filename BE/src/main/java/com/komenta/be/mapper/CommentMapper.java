package com.komenta.be.mapper;

import com.komenta.be.model.comment.*;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    public int insertComment(CommentInfoDTO comment_info);
    public List<VodEpisodeCommentDTO> getVodEpisodeComment(CommentListByVeIdDTO comment_info);
    public List<CommentRankDTO> getCommentRankList();
    public List<MyCommentDTO> getMyComment(int u_id);
    public int isCommentGood(CommentGoodDTO comment_good);
    public int addLikeComment(CommentGoodDTO comment_good);
    public int cancelLikeComment(CommentGoodDTO comment_good);
    int deleteComment(CommentInfoDTO dto);
}