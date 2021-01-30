package com.komenta.be.mapper;

import com.komenta.be.model.vod.VodEpisodeAllDTO;
import com.komenta.be.model.vod.VodHistoryDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VodMapper {
    VodHistoryDTO selectHistoryById(int u_id);

    VodEpisodeAllDTO selectEpisodeById(int ve_id);
}
