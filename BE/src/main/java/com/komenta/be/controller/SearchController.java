package com.komenta.be.controller;

import com.komenta.be.model.member.MemberDTO;
import com.komenta.be.model.member.MemberSearchInfoDTO;
import com.komenta.be.model.vod.VodDTO;
import com.komenta.be.model.vod.VodEpisodeAllDTO;
import com.komenta.be.model.vod.VodSearchInfoDTO;
import com.komenta.be.service.MemberService;
import com.komenta.be.service.VodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/search")
@CrossOrigin(origins = "*")
public class SearchController {
    @Autowired
    VodService vodService;

    @Autowired
    MemberService memberService;

    @GetMapping("/list_vod")
    public List<VodSearchInfoDTO> selectAllVod() {
        return vodService.getAllVod();
    }

    @GetMapping("/list_member")
    public List<MemberSearchInfoDTO> selectAllMember(){
        return memberService.getAllMember();
    }

}
