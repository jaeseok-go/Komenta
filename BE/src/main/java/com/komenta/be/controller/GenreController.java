package com.komenta.be.controller;

import com.komenta.be.model.genre.GenreDTO;
import com.komenta.be.model.genre.GenreDetailDTO;
import com.komenta.be.service.GenreService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/genre")
public class GenreController {

    @Autowired
    GenreService genreService;

    @ApiOperation(value = "모든 장르 조회", notes = "모든 장르 정보를 반환")
    @GetMapping("/list_genre")
    public List<GenreDTO> selectAllGenre(){
        return genreService.selectAllGenre();
    }


    @ApiOperation(value = "장르 하위의 세부 장르 조회", notes = "장르 하위의 세부 장르 정보를 반환")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "g_id", value = "대분류 장르 아이디", dataType = "int", required = true)
    })
    @GetMapping("/list_genre_detail")
    public List<GenreDetailDTO> selectAllGenreDetail(int g_id){
        return genreService.selectAllGenreDetail(g_id);
    }

}
