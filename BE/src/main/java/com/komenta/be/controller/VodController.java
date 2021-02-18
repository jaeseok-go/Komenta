    package com.komenta.be.controller;

    import com.komenta.be.model.vod.*;
    import com.komenta.be.service.JwtService;
    import com.komenta.be.service.VodService;
    import io.swagger.annotations.ApiImplicitParam;
    import io.swagger.annotations.ApiImplicitParams;
    import io.swagger.annotations.ApiOperation;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import javax.servlet.http.HttpServletRequest;
    import java.util.HashMap;
    import java.util.LinkedList;
    import java.util.List;
    import java.util.Map;

    @CrossOrigin(origins = "*")
    @RestController
    @RequestMapping(value = "/vod")
    public class VodController {

        @Autowired
        JwtService jwtService;

        @Autowired
        VodService vodService;




        @ApiOperation(value = "해당 회차 VOD 상세정보", notes = "ve_id랑 u_id 값으로 한 에피소드에 대한 모든 정보 받기")
        @ApiImplicitParams({
                @ApiImplicitParam(name = "ve_id", value = "회차 아이디", dataType = "int", required = true)
        })
        @GetMapping("/vodnum/{ve_id}")
        public VodDetailDTO selectOneEpisode(@PathVariable int ve_id, HttpServletRequest request){
            int u_id = jwtService.getUidFromJwt(request.getHeader("auth-token"));
            VodHistorySetDTO history_info = new VodHistorySetDTO(u_id, ve_id);


            VodDetailDTO result = vodService.getVodDetail(history_info);


            return result;
        }





        @ApiOperation(value = "나의 시청기록들 보기", notes = "u_id 입력 받고 내가 시청했던 기록들 보기")
        @GetMapping("/myvod")
        public ResponseEntity<Map<String, Object>> selectMyVod(HttpServletRequest request) {
            // jwt 토큰의 uid 를 받는다는 가정하에
            String token = request.getHeader("auth-token");

            int u_id = (int) jwtService.getUidFromJwt(token);
            HttpStatus status = null;
            Map<String, Object> resultMap = new HashMap<>();
            List<VodEpisodeAllDTO> list = new LinkedList<>();
            try {
                List<VodHistoryDTO> history = vodService.selectHistoryById(u_id);
                for (VodHistoryDTO dt : history) {
                    VodEpisodeAllDTO episode = vodService.selectEpisodeById(dt.getVe_id());
                    list.add(episode);
                }
                resultMap.put("episodeList", list);
                resultMap.put("historyList", history);
                status = HttpStatus.OK;

            } catch (RuntimeException e) {
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
            return new ResponseEntity<Map<String, Object>>(resultMap, status);
        }


        @ApiOperation(value = "최신순 VOD 회차 조회", notes = "업로드 된 순으로 VOD 회차 조회")
        @GetMapping("/list_recent")
        public List<VodEpisodeDTO> getVodListRecent(){
                return vodService.getVodListRecent();
        }


        @ApiOperation(value = "댓글순 VOD 회차 조회", notes = "댓글 수가 많은 순으로 VOD 회차 조회")
        @GetMapping("/list_comment")
        public List<VodEpisodeDTO> getVodListComment(){
            return vodService.getVodListComment();
        }



        @ApiOperation(value = "조회순 VOD 회차 조회", notes = "조회 수가 많은 순으로 VOD 회차 조회")
        @GetMapping("/list_watching")
        public List<VodEpisodeDTO> getVodListWatching(){
            return vodService.getVodListWatching();
        }



        @ApiOperation(value = "VOD 세부 내용 조회", notes = "입력받은 v_id로 해당 VOD의 세부내용 반환")
        @GetMapping("/vod_info/{v_id}")
        public List<VodInfoForUserDTO> getVodInfoForUser(@PathVariable("v_id") int v_id){
            return vodService.getVodInfoForUser(v_id);
        }

        @ApiOperation(value = "장르 대분류별 VOD 선택 조회", notes = "입력받은 g_id로 VOD의 목록 반환")
        @GetMapping("/list_genre")
        public List<VodInfoByGenreDTO> getVodListGenre(int g_id){
            return vodService.getVodListGenre(g_id);
        }

        @ApiOperation(value = "장르 소분류별 VOD 선택 조회", notes = "입력받은 gd_id로 VOD의 목록 반환")
        @GetMapping("/list_genre_detail")
        public List<VodInfoByGenreDTO> getVodListGenreDetail(int gd_id){
            return vodService.getVodListGenreDetail(gd_id);
        }

        @ApiOperation(value = "VOD 시청 시작", notes = "VOD 시청 시작할 때 시청기록이 있으면 그냥 시작, 없으면 시청기록 만들어서 결과 반환")
        @ApiImplicitParams({
                @ApiImplicitParam(name = "ve_id", value = "회차 아이디", dataType = "int", required = true)
        })
        @PostMapping("/start_watching/{ve_id}")
        public int startWatching(@PathVariable("ve_id") int ve_id, HttpServletRequest request){
            String token = request.getHeader("auth-token");
            int u_id = (int) jwtService.get(token).get("u_id");

            VodHistorySetDTO history = new VodHistorySetDTO(u_id, ve_id);
            if(vodService.getVodHistoryByUAndVe(history) == 0) {
                return vodService.insertVodHistory(history);
            }
            return 0;
        }


        @ApiOperation(value = "VOD 시청 끝", notes = "VOD 시청 끝날때 입력받은 VOD 회차를 히스토리 현재 시간으로 업데이트해서 결과 반환")
        @ApiImplicitParams({
                @ApiImplicitParam(name = "history", value = "ve_id(회차 아이디), vh_watching_time(VOD 시청중인 시간(HH:MM:SS))",
                        dataType = "VodUpdateTimeDTO", required = true)
        })
        @PutMapping("/end_watching")
        public int endWatching(@RequestBody VodUpdateTimeDTO history, HttpServletRequest request){
            int u_id = (int) jwtService.getUidFromJwt(request.getHeader("auth-token"));
            history.setU_id(u_id);

            return vodService.updateTime(history);
        }

    }
