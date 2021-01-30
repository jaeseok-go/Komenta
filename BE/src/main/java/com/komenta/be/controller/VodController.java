package com.komenta.be.controller;

import com.komenta.be.model.vod.VodEpisodeAllDTO;
import com.komenta.be.model.vod.VodHistoryDTO;
import com.komenta.be.service.AdminService;
import com.komenta.be.service.VodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/vod")
public class VodController {

    @Autowired
    VodService vodService;
    @GetMapping("/vodnum/{ve_id}")
    public ResponseEntity<Map<String, Object>> selectOneEpisode(@PathVariable int ve_id, int u_id){
        // 회원 정보를 받는다.
        // 1. jwt에서 u_id값을 받아오는 방법
        // 2. 실패시 그냥 u_id를 파라미터로 받아오는 방법

        // 회원이 봤던거 인지 아닌지
        // 봤으면 시청 시간을 같이 response해주어야함.
        VodHistoryDTO history = vodService.selectHistoryById(u_id);

        // episode에 대한 정보를 모두 받아옴
        VodEpisodeAllDTO episode = vodService.selectEpisodeById(ve_id);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("vh_id", history.getVh_id());
        resultMap.put("vh_wathching_time", history.getVh_watching_time());
        resultMap.put("episodeInfo", episode);

        return new ResponseEntity<Map<String, Object>(resultMap);
    }
}
