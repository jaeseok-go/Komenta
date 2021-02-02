    package com.komenta.be.controller;

    import com.komenta.be.model.vod.VodEpisodeAllDTO;
    import com.komenta.be.model.vod.VodHistoryDTO;
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
    import javax.servlet.http.HttpServletResponse;
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

        @ApiOperation(value = "해당 회차 VOD 보기", notes = "ve_id랑 u_id 값으로 한 에피소드에 대한 모든 정보 받기")
        @ApiImplicitParams({
                @ApiImplicitParam(name = "vodEpisodeId", value = "ve_id(ve 번호)", dataType = "int", required = true),
        })
        @GetMapping("/vodnum/{ve_id}")
        public ResponseEntity<Map<String, Object>> selectOneEpisode(@PathVariable int ve_id, HttpServletRequest request){
            // 회원 정보를 받는다.
            // 1. jwt에서 u_id값을 받아오는 방법
            String token = request.getHeader("auth-token");
            int u_id = jwtService.getUidFromJwt(token);
            // 회원이 봤던거 인지 아닌지
            // 봤으면 시청 시간을 같이 response해주어야함.
            HttpStatus status = null;
            Map<String, Object> resultMap = new HashMap<>();
            try {
                List<VodHistoryDTO> history = vodService.selectHistoryById(u_id);
                if(history.isEmpty()){
                    VodEpisodeAllDTO episode = vodService.selectEpisodeById(ve_id);
                    resultMap.put("episodeInfo", episode);
                }
                else{
                    for(VodHistoryDTO dt : history){
                        if(dt.getVe_id() == ve_id){
                            VodEpisodeAllDTO episode = vodService.selectEpisodeById(ve_id);
                            resultMap.put("vh_id", dt.getVh_id());
                            resultMap.put("vh_watching_time", dt.getVh_watching_time());
                            resultMap.put("episodeInfo", episode);
                            System.out.println(episode);
                            // episode에 대한 정보를 모두 받아옴
                            break;
                        }
                    }
                }
                status = HttpStatus.OK;
            }
            catch(RuntimeException e){
                resultMap.put("message", e.getMessage());
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
            return new ResponseEntity<Map<String, Object>>(resultMap,status);
        }

        @ApiOperation(value = "나의 시청기록들 보기", notes = "u_id 입력 받고 내가 시청했던 기록들 보기")
        @GetMapping("/myvod")
        public ResponseEntity<Map<String, Object>> selectMyVod(HttpServletRequest request) {
            // jwt 토큰의 uid 를 받는다는 가정하에
            String token = request.getHeader("auth-token");
            int u_id = (int) jwtService.get(token).get("u_id");
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
                resultMap.put("message", e.getMessage());
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
            return new ResponseEntity<Map<String, Object>>(resultMap, status);
        }
    }
