package com.komenta.be.service;

import com.komenta.be.model.vod.VodEpisodeAllDTO;
import com.komenta.be.model.vod.VodHistoryDTO;

import java.util.List;

public interface VodService {
    List<VodHistoryDTO> selectHistoryById(int u_id);

    VodEpisodeAllDTO selectEpisodeById(int ve_id);
}
