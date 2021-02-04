package com.komenta.be.mapper;

import com.komenta.be.model.playlist.PlayListDTO;

import java.util.List;

public interface PlayListMapper {
    int createPlayList(PlayListDTO dto);
    List<PlayListDTO> getPlayListById(int u_id);
}
