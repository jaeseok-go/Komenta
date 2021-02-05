package com.komenta.be.service;

import com.komenta.be.model.playlist.*;

import java.util.List;

public interface PlayListService {
    public int createPlayList(PlayListDTO dto);
    public int playlist_info_modify(PlayListDTO dto);
    public int playlist_delete(int pl_id);
    public List<PlayListGetAllDTO> playlist_info(int pl_id);
    public List<Integer> select_favorite_pl_id(int u_id);
    public List<Integer> select_regist_pl_id(int u_id);
    public List<Integer> getBestPlayList();
    public int moveHistoryToPlaylist(PlayListFromHistoryDTO move_info);
    public int movePlaylistToPlaylist(PlayListFromPlayListDTO move_info);
    public List<PlayListDetailDTO> getPlayListDetail(int pl_id);
}
