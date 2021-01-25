package com.komenta.be.mapper;

import com.komenta.be.model.genre.GenreDTO;
import com.komenta.be.model.genre.GenreDetailDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface GenreMapper {

    List<GenreDTO> selectAllGenre();
    List<GenreDetailDTO> selectAllGenreDetail(int g_id);
}
