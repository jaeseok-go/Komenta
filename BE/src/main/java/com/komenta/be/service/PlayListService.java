package com.komenta.be.service;

import com.komenta.be.model.playlist.PlayListDTO;
import com.komenta.be.model.playlist.PlayListFromHistoryDTO;
import com.komenta.be.model.playlist.PlayListGetAllDTO;

import java.util.List;

public interface PlayListService {
    public int createPlayList(PlayListDTO dto);
    public int playlist_info_modify(PlayListDTO dto);
    public int playlist_delete(int pl_id);
    public List<PlayListGetAllDTO> playlist_info(int pl_id);
    public List<Integer> select_favorite_pl_id(int u_id);
    public List<Integer> select_regist_pl_id(int u_id);
    public int moveHistoryToPlaylist(PlayListFromHistoryDTO move_info);
}
