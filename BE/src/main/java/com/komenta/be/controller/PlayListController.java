package com.komenta.be.controller;

import com.komenta.be.model.playlist.PlayListContentsDTO;
import com.komenta.be.model.playlist.PlayListDTO;
import com.komenta.be.model.playlist.PlayListGetAllDTO;
import com.komenta.be.service.JwtService;
import com.komenta.be.service.PlayListService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/playlist")
public class PlayListController {
    /*
        유저가 시청한 콘텐츠 목록 조회(스토어 관리)
        유저가 등록한 플레이리스트 목록
        v 플레이리스트 생성
        플레이리스트 수정
        플레이리스트 삭제
        플레이리스트에 VOD추가
        플레이리스트 상세 페이지
        플레이리스트의 컨텐츠 상세보기
        플레이리스트의 컨텐츠 리뷰 생성
        플레이리스트 좋아요
        골라보는 플레이 리스트
        좋아요 누른 스트리밍 리스트(스토어관리)

    */

    @Autowired
    PlayListService playListService;

    @Autowired
    JwtService jwtService;

    @ApiOperation(value = "PlayList 생성", notes = "나의 PlayList (틀)을 만들어 줌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pl_name", value = "playlist name", dataType = "String", required = true),
            @ApiImplicitParam(name = "pl_comment", value = "playlist comment", dataType = "String", required = true)
    })
    @PostMapping("/plist_regist")
    public int createPlayList(@RequestBody PlayListDTO pldto, HttpServletRequest request){
        String token = request.getHeader("auth-token");
        int u_id = jwtService.getUidFromJwt(token);
//        int u_id = 1;
        pldto.setU_id(u_id);
        return playListService.createPlayList(pldto);
    }

    @PutMapping("/plist_update")
    public int updatePlayList(@RequestBody PlayListDTO pldto, HttpServletRequest request){
        // 1. 입력 받은 플레이리스트 컨텐츠 dto로 변경
        return playListService.playlist_info_modify(pldto);
    }

    @DeleteMapping("/plist_delete")
    public int deletePlayList(@RequestParam("pl_id") int pl_id, HttpServletRequest request){
        // 1. u_id, pl_id 로 플레이 리스트 굿에서 지우기
        return playListService.playlist_delete(pl_id);
    }

    @GetMapping("/my_plist/{u_id}")
    public ResponseEntity<List<PlayListGetAllDTO>> myPlayList(@PathVariable("u_id") int u_id){
        HttpStatus status = null;
        List<PlayListGetAllDTO> dtolist = new ArrayList<>();
        try{

            List<Integer> pl_id = playListService.select_regist_pl_id(u_id);


            for(int a : pl_id){
                dtolist.add((PlayListGetAllDTO) playListService.playlist_info(a));
            }
            status = HttpStatus.OK;
        }
        catch(RuntimeException e){
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<List<PlayListGetAllDTO>>(dtolist, status);
    }
    @GetMapping("/my_favorite_plist/{u_id}")
    public ResponseEntity<List<PlayListGetAllDTO>> myFavoritePlayList(@PathVariable("u_id") int u_id){
        HttpStatus status = null;
        List<PlayListGetAllDTO> dtolist = new ArrayList<>();
        try {
            List<Integer> pl_id = playListService.select_favorite_pl_id(u_id);
            for (int a : pl_id) {
                dtolist.add((PlayListGetAllDTO) playListService.playlist_info(a));
            }

            status = HttpStatus.OK
        }
        catch(RuntimeException e){
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<List<PlayListGetAllDTO>>(dtolist, status);
    }
//    @GetMapping("/otherplist")
//    public ResponseEntity<List<PlayListDTO>> myPlayList(HttpServletRequest request){
//        String token = request.getHeader("auth-token");
//        int u_id = jwtService.getUidFromJwt(token);
//        // 남이 만든 플레이리스트, 내가 좋아요 한 보기
//        // 1. 먼저 플레이리스트 굿을 살펴본다 (uid) => pl id
//        // 2. pl_id와 내 u_id가 아닌 play list를 가져온다
//        // 3. 각 플레이 리스트에서 play list contents를 가져온다 (pl_id)
//        // 4. play list contents에서 vod history를 가져온다
//        // 5. vod history에서 vod episod_all을 가져온다.
//        return new ResponseEntity<List<PlayListDTO>>(playListService.getPlayListById(u_id), HttpStatus.ACCEPTED);
//    }

//    @GetMapping("/bestplist")
//    public ResponseEntity<List<PlayListDTO>> bestPList(){
//        // 1. pl_id로 플레이리스 굿을 그룹화
//        // 2. 그룹화 된 pl_id count를 세서 가장 큰거 10개정도
//        // 3. where 절로 get playlist
//        return new ResponseEntity<List<PlayListDTO>>(playListService.getPlayListById(u_id), HttpStatus.ACCEPTED);
//    }
}

