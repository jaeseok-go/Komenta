package com.komenta.be.service;

import com.komenta.be.model.comment.*;

import java.util.List;

public interface CommentService {
    public int insertComment(CommentInfoDTO comment_info);

    public List<VodEpisodeCommentDTO> getVodEpisodeComment(int ve_id);

    public List<CommentRankDTO> getCommentRankList();

    public List<MyCommentDTO> getMyComment(int u_id);

    public int isCommentGood(CommentGoodDTO comment_good);
    public int addLikeComment(CommentGoodDTO comment_good);
    public int cancelLikeComment(CommentGoodDTO comment_good);

}
