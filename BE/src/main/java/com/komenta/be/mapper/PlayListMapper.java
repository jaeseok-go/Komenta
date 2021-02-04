package com.komenta.be.mapper;

import com.komenta.be.model.playlist.PlayListDTO;
import com.komenta.be.model.playlist.PlayListGetAllDTO;

import java.util.List;

public interface PlayListMapper {
    int createPlayList(PlayListDTO dto);
    int playlist_info_modify(PlayListDTO dto);
    int playlist_delete(int pl_id);
    List<PlayListGetAllDTO> playlist_info(int pl_id);
    List<Integer> select_favorite_pl_id(int u_id);
    List<Integer> select_regist_pl_id(int u_id);
}
