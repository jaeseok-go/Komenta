package com.komenta.be.mapper;

import com.komenta.be.model.vod.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VodMapper {
    List<VodHistoryDTO> selectHistoryById(int u_id);

    VodEpisodeAllDTO selectEpisodeById(int ve_id);

    List<VodEpisodeDTO> getVodListRecent();

    List<VodEpisodeDTO> getVodListComment();

    List<VodEpisodeDTO> getVodListWatching();

    List<VodInfoForUserDTO> getVodInfoForUser(int v_id);

    List<VodInfoByGenreDTO> getVodListGenre(int g_id);

    List<VodInfoByGenreDTO> getVodListGenreDetail(int gd_id);
}
