package com.komenta.be.mapper;

import com.komenta.be.model.comment.CommentInfoDTO;
import com.komenta.be.model.comment.CommentRankDTO;
import com.komenta.be.model.comment.VodEpisodeCommentDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    public int insertComment(CommentInfoDTO comment_info);
    public List<VodEpisodeCommentDTO> getVodEpisodeComment(int ve_id);
    public List<CommentRankDTO> getCommentRankList();
}
