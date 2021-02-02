package com.komenta.be.controller;

import com.komenta.be.model.playlist.PlayListDTO;
import com.komenta.be.service.JwtService;
import com.komenta.be.service.PlayListService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/playlist")
public class PlayListController {
    @Autowired
    PlayListService playListService;
    @Autowired
    JwtService jwtService;

    @ApiOperation(value = "PlayList 생성", notes = "나의 PlayList (틀)을 만들어 줌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pl_name", value = "playlist name", dataType = "String", required = true),
            @ApiImplicitParam(name = "pl_comment", value = "playlist comment", dataType = "String", required = true)
    })
    @PostMapping("/plcreate")
    public int createPlayList(@RequestBody PlayListDTO pldto, HttpServletRequest request){
        String token = request.getHeader("auth-token");
        int u_id = jwtService.getUidFromJwt(token);
        pldto.setU_id(u_id);
        return playListService.createPlayList(pldto);
    }

//    @GetMapping("/plget")
//    public ResponseEntity<List<PlayListDTO>> playlistList()

}

