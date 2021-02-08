package com.komenta.be.controller;

import com.komenta.be.model.member.MemberDTO;
import com.komenta.be.model.playlist.*;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    @ApiOperation(value = "플레이리스트 생성", notes = "나의 플레이리스트 (틀)을 만들어 줌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "playlist", value = "pl_name(플레이리스트 이름), pl_comment(플레이리스트에 대한 리뷰)", dataType = "PlayListDTO", required = true),
    })
    @PostMapping("/plist_regist")
    public int createPlayList(@RequestBody PlayListDTO playlist, HttpServletRequest request){
        String token = request.getHeader("auth-token");
        int u_id = jwtService.getUidFromJwt(token);
//      int u_id = 1;
        playlist.setU_id(u_id);
        return playListService.createPlayList(playlist);
    }


    @ApiOperation(value = "플레이리스트 수정", notes = "나의 플레이리스트의 이름이나 리뷰를 수정해서 결과를 반환")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "playlist", value = "pl_name(플레이리스트 이름), pl_comment(플레이리스트에 대한 리뷰)", dataType = "PlayListDTO", required = true),
    })
    @PutMapping("/plist_update")
    public int updatePlayList(@RequestBody PlayListDTO playlist, HttpServletRequest request){
        // 1. 입력 받은 플레이리스트 컨텐츠 dto로 변경
        return playListService.playlist_info_modify(playlist);
    }



    @ApiOperation(value = "플레이리스트 삭제", notes = "입력받은 pl_id로 나의 플레이리스트를 삭제하고 그 결과를 반환")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pl_id", value = "플레이리스트 아이디", dataType = "int", required = true),
    })
    @DeleteMapping("/plist_delete")
    public int deletePlayList(@RequestParam("pl_id") int pl_id, HttpServletRequest request){
        // 1. u_id, pl_id 로 플레이 리스트 굿에서 지우기
        return playListService.playlist_delete(pl_id);
    }



    @ApiOperation(value = "회원이 등록한 플레이리스트 목록 조회", notes = "입력받은 u_id의 플레이리스트들의 목록을 반환")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "u_id", value = "플레이리스트 주인의 회원 아이디", dataType = "int", required = true),
    })
    @GetMapping("/get_plist_list/{u_id}")
    public ResponseEntity<List<List<PlayListGetAllDTO>>> myPlayList(@PathVariable("u_id") int u_id){
        HttpStatus status = null;
        List<List<PlayListGetAllDTO>> dtolist = new ArrayList<>();
        try{

            List<Integer> pl_id = playListService.select_regist_pl_id(u_id);


            for(int a : pl_id){
                dtolist.add(playListService.playlist_info(a));
            }
            status = HttpStatus.OK;
        }
        catch(RuntimeException e){
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<List<List<PlayListGetAllDTO>>>(dtolist, status);
    }


    @ApiOperation(value = "회원이 좋아요한 플레이리스트 목록 조회", notes = "입력받은 u_id가 좋아요한 플레이리스트들의 목록을 반환")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "u_id", value = "플레이리스트를 좋아요한 회원 아이디", dataType = "int", required = true),
    })
    @GetMapping("/get_favorite_plist/{u_id}")
    public ResponseEntity<List<List<PlayListGetAllDTO>>> myFavoritePlayList(@PathVariable("u_id") int u_id){
        HttpStatus status = null;
        List<List<PlayListGetAllDTO>> dtolist = new ArrayList<>();
        try {
            List<Integer> pl_id = playListService.select_favorite_pl_id(u_id);
            for (int a : pl_id) {
                dtolist.add(playListService.playlist_info(a));
            }

            status = HttpStatus.OK;
        }
        catch(RuntimeException e){
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<List<List<PlayListGetAllDTO>>>(dtolist, status);
    }

    @ApiOperation(value = "플레이 리스트 좋아요", notes = "pl_id을 입력받고 jwt 토큰에 u_id를 받아서 좋아요 추가")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pl_id", value = "좋아요한 pl id", dataType = "int", required = true)
    })
    @PostMapping("/like")
    public int likePList(@RequestParam("pl_id") int pl_id, HttpServletRequest request){
        int u_id = jwtService.getUidFromJwt(request.getHeader("auth-token"));
        PlayListGoodDTO dto = new PlayListGoodDTO(pl_id, u_id);
        return playListService.likePList(dto);

    }

    @ApiOperation(value = "플레이 리스트 좋아요 취소", notes = "pl_id을 입력받고 jwt 토큰에 u_id를 받아서 좋아요 삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pl_id", value = "좋아요한 pl id", dataType = "int", required = true)
    })
    @PostMapping("/unlike")
    public int unLikePList(@RequestParam("pl_id") int pl_id, HttpServletRequest request){
        int u_id = jwtService.getUidFromJwt(request.getHeader("auth-token"));
        PlayListGoodDTO dto = new PlayListGoodDTO(pl_id, u_id);
        return playListService.unLikePList(dto);

    }


    @ApiOperation(value = "전체 플레이리스트 중 탑 10", notes = "전체 플레이 리스트 중 좋아요를 받은 플레이리스트 탑 10")
    @GetMapping("/bestplist")
    public ResponseEntity<List<PlaytListBestDTO> > bestPList(){
        // pl_id, pl_id 갯수, rank, pldetail list를 담은 List
        List<PlaytListBestDTO> list= playListService.getBestPlayList();
        for(PlaytListBestDTO a : list){
            a.setPldetail(playListService.getPlayListDetail(a.getPlb_id()));
        }
        return new ResponseEntity<List<PlaytListBestDTO> >(list, HttpStatus.ACCEPTED);
    }



    @ApiOperation(value = "나의 시청 기록에서 플레이리스트에 VOD 추가", notes = "나의 시청기록에서 시청기록을 끌어당겨 플레이리스트에 추가")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "move_info", value = "vh_name(옮겨질 시청기록 아이디), pl_id(옮길 플레이리스트 아이디)", dataType = "PlayListFromHistoryDTO", required = true),
    })
    @PostMapping("/move_history_to_playlist")
    public int moveHistoryToPlaylist(@RequestBody PlayListFromHistoryDTO move_info){
        return playListService.moveHistoryToPlaylist(move_info);
    }



    @ApiOperation(value = "나의 시청 기록에서 플레이리스트에 VOD 추가", notes = "나의 시청기록에서 시청기록을 끌어당겨 플레이리스트에 추가")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "move_info", value = "plc_id(옮겨질 플레이리스트 콘텐츠 아이디), pl_id(옮길 플레이리스트 아이디)", dataType = "PlayListFromPlayListDTO", required = true),
    })
    @PostMapping("/move_playlist_to_playlist")
    public int movePlaylistToPlaylist(@RequestBody PlayListFromPlayListDTO move_info){
        return playListService.movePlaylistToPlaylist(move_info);
    }


    @ApiOperation(value = "플레이리스트 상세 내용 조회", notes = "입력받은 pl_id의 플레이리스트 정보를 반환")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pl_id", value = "플레이리스트 아이디", dataType = "int", required = true),
    })
    @GetMapping("/playlist_detail/{pl_id}")
    public List<PlayListDetailDTO> getPlayListDetail(@PathVariable("pl_id") int pl_id){
        return playListService.getPlayListDetail(pl_id);
    }



    @ApiOperation(value = "플레이리스트 컨텐츠의 상세보기", notes = "입력받은 plc_id로 제목, 회차, 리뷰 등을 보여줌")
    @GetMapping("/contents_detail/{plc_id}")
    public ContentDetailDTO getContentDetail(@PathVariable("plc_id") int plc_id){
        return playListService.getContentDetail(plc_id);
    }


    @ApiOperation(value = "플레이리스트의 컨텐츠 리뷰 입력", notes = "내가 등록한 플레이리스트의 각 컨텐츠에 리뷰다는 기능")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "review", value = "plc_id(컨텐츠 아이디), vh_comment(컨텐츠에 대한 리뷰)", dataType = "AddContentsReviewDTO", required = true),
    })
    @PutMapping("/add_contents_review")
    public int addContentsReview(@RequestBody AddContentsReviewDTO review){
        // 1. 입력 받은 플레이리스트 컨텐츠 dto로 변경
        return playListService.addContentsReview(review);
    }

}

