package com.komenta.be.service;

import com.komenta.be.model.genre.GenreDTO;
import com.komenta.be.model.genre.GenreDetailDTO;

import java.util.List;

public interface GenreService {

    public List<GenreDTO> selectAllGenre();
    public List<GenreDetailDTO> selectAllGenreDetail(int g_id);
}
