package com.komenta.be.service;

import com.komenta.be.model.comment.CommentInfoDTO;
import com.komenta.be.model.comment.VodEpisodeCommentDTO;

import java.util.List;

public interface CommentService {
    public int insertComment(CommentInfoDTO comment_info);

    public List<VodEpisodeCommentDTO> getVodEpisodeComment(int ve_id);
}
