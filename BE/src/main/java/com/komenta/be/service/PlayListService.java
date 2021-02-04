package com.komenta.be.service;

import com.komenta.be.model.playlist.PlayListDTO;

import java.util.List;

public interface PlayListService {
    public int createPlayList(PlayListDTO dto);
    public List<PlayListDTO> getPlayListById(int u_id);
}
