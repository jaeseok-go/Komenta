package com.komenta.be.mapper;

import com.komenta.be.model.comment.*;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    public int insertComment(CommentInfoDTO comment_info);
    public List<VodEpisodeCommentDTO> getVodEpisodeComment(int ve_id);
    public List<CommentRankDTO> getCommentRankList();
    public List<MyCommentDTO> getMyComment(int u_id);
    public int addLikeComment(CommentGoodDTO dto);
    public int cancelLikeComment(CommentGoodDTO dto);
}