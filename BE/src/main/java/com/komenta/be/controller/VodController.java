package com.komenta.be.controller;

import com.komenta.be.model.vod.VodEpisodeAllDTO;
import com.komenta.be.model.vod.VodHistoryDTO;
import com.komenta.be.service.JwtService;
import com.komenta.be.service.VodService;
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

    @GetMapping("/vodnum/{ve_id}")
    public ResponseEntity<Map<String, Object>> selectOneEpisode(@PathVariable int ve_id, int u_id){
        // 회원 정보를 받는다.
        // 1. jwt에서 u_id값을 받아오는 방법
        // 2. 실패시 그냥 u_id를 파라미터로 받아오는 방법
        // 회원이 봤던거 인지 아닌지
        // 봤으면 시청 시간을 같이 response해주어야함.
        HttpStatus status = null;
        Map<String, Object> resultMap = new HashMap<>();
        try {
            List<VodHistoryDTO> history = vodService.selectHistoryById(u_id);
            for(VodHistoryDTO dt : history){
                if(dt.getVe_id() == ve_id){
                    VodEpisodeAllDTO episode = vodService.selectEpisodeById(ve_id);
                    resultMap.put("vh_id", dt.getVh_id());
                    resultMap.put("vh_watching_time", dt.getVh_watching_time());
                    resultMap.put("episodeInfo", episode);
                    // episode에 대한 정보를 모두 받아옴
                    break;
                }
            }
            status = HttpStatus.OK
        }
        catch(RuntimeException e){
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap,status);
    }

    @GetMapping("/myvod")
    public ResponseEntity<Map<String, Object>> selectMyVod(HttpServletRequest request) {
        // jwt 토큰의 uid 를 받는다는 가정하에
        String token = request.getHeader("auth-token");
        int u_id = (int) jwtService.get(token).get("u_id");
        HttpStatus status = null;
        Map<String, Object> resultMap = new HashMap<>();
        List<VodEpisodeAllDTO> list = new LinkedList<>();
        try {
            List<VodHistoryDTO> history = (List<VodHistoryDTO>) vodService.selectHistoryById(u_id);
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
