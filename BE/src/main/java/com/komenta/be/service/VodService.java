package com.komenta.be.service;

import com.komenta.be.model.vod.*;

import java.util.List;

public interface VodService {
    List<VodHistoryDTO> selectHistoryById(int u_id);

    VodEpisodeAllDTO selectEpisodeById(int ve_id);

    List<VodEpisodeDTO> getVodListRecent();

    List<VodEpisodeDTO> getVodListComment();

    List<VodEpisodeDTO> getVodListWatching();

    List<VodInfoForUserDTO> getVodInfoForUser(int v_id);

    List<VodInfoByGenreDTO> getVodListGenre(int g_id);

    List<VodInfoByGenreDTO> getVodListGenreDetail(int gd_id);


}
