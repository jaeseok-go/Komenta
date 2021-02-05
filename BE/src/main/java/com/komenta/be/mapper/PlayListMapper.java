package com.komenta.be.mapper;

import com.komenta.be.model.playlist.*;

import java.util.List;

public interface PlayListMapper {
    int createPlayList(PlayListDTO dto);
    int playlist_info_modify(PlayListDTO dto);
    int playlist_delete(int pl_id);
    List<PlayListGetAllDTO> playlist_info(int pl_id);
    List<Integer> select_favorite_pl_id(int u_id);
    List<Integer> select_regist_pl_id(int u_id);
    int moveHistoryToPlaylist(PlayListFromHistoryDTO move_info);
    int movePlaylistToPlaylist(PlayListFromPlayListDTO move_info);
    public List<PlayListDetailDTO> getPlayListDetail(int pl_id);

    List<PlaytListBestDTO> getBestPlayList();

    int likePList(PlayListGoodDTO dto);

    int unLikePList(PlayListGoodDTO dto);
}
