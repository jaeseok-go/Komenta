package com.komenta.be.controller;

import com.komenta.be.model.admin.ReportListDTO;
import com.komenta.be.model.member.MemberDTO;
import com.komenta.be.model.vod.VodDTO;
import com.komenta.be.model.vod.VodEpisodeAllDTO;
import com.komenta.be.model.vod.VodEpisodeDTO;
import com.komenta.be.service.AdminService;
import com.komenta.be.service.GenreService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value ="/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    GenreService genreService;



    @ApiOperation(value = "회원목록 조회", notes = "모든 회원 정보를 리스트로 반환")
    @GetMapping("/member_list")
    public List<MemberDTO> selectAllMembers(){
        return adminService.selectAllMember();
    }





    @ApiOperation(value = "회원정보 수정", notes = "회원 정보를 받아서 update 후 결과 반환")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "member", value = "u_id(회원번호), u_email(이메일), u_pw(비밀번호), u_nickname(닉네임), u_phone_number(휴대전화 번호)," +
                    " u_expire_member(멤버쉽 종료일자), u_is_admin(관리자 여부), u_profile_pic(프로필 사진 경로), u_is_blocked(댓글 기능 제한 여부)", dataType = "MemberDTO", required = true)
    })
    @PutMapping("/member_update")
    public int updateMember(@RequestBody MemberDTO member){
        return adminService.updateMember(member);
    }





    @ApiOperation(value = "정보가 없던 VOD 업로드", notes = "VOD 정보를 입력받아 VOD 회차를 업로드할 수 있는 VOD 등록")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "vod", value = "VDO 정보", dataType = "VodDTO", required = true),
            @ApiImplicitParam(name = "vod", value = "VDO Episode 정보", dataType = "VodEpisodeDTO", required = true)
    })
    @PostMapping("/vod_regist")
    public int registVod(VodDTO vdto, VodEpisodeDTO vedto, @RequestParam("file") MultipartFile multipartFile){
        int vodsuc = 0;
        if(vdto.getV_id() == 0){
            vodsuc = adminService.registVod(vdto);
            System.out.println("vod regist test "+vodsuc);
        }
        else{ vodsuc = 1;}
        int vodeip = adminService.uploadEpisode(vedto);
        System.out.println("vod episode regist test"+vodeip);
        /*
         * 파일 업로드
         */
        if(vodeip == 1) {
            // 100톰과제리50.mp4
            String file_name = "/home/ubuntu/Video/" + vedto.getVe_id() + vdto.getV_title() + vedto.getVe_episode_num() + ".mp4";
            file_name.replace(" ", "_");

            File targetFile = new File(file_name);
            System.out.println(targetFile);

            try {
                InputStream fileStream = multipartFile.getInputStream();
                FileUtils.copyInputStreamToFile(fileStream, targetFile);
                System.out.println("파일 업로드 성공");
            } catch (IOException e) {
                FileUtils.deleteQuietly(targetFile);
                e.printStackTrace();
            }
        }

        return vodsuc + vodeip -1;
    }






    @ApiOperation(value = "VOD 목록 조회", notes = "모든 VOD 정보를 조회")
    @GetMapping("/vod_list")
    public ResponseEntity<List<VodDTO>> selectAllVod(){
        return new ResponseEntity<List<VodDTO>>(adminService.selectAllVod(), HttpStatus.OK);
    }






    @ApiOperation(value = "장르별 VOD 리스트 조회", notes = "디테일 장르 id를 url 파라미터로 입력받아 장르별 VOD 리스트를 반환")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "gd_id", value = "디테일 장르 아이디", dataType = "int", required = true)
    })
    @GetMapping("/vod_list_by_gdid/{gd_id}")
    public ResponseEntity<List<VodDTO>> selectVodByGd(@PathVariable int gd_id){
        return new ResponseEntity<List<VodDTO>>(adminService.selectVodByGd(gd_id), HttpStatus.OK);
    }





    @ApiOperation(value = "VOD 정보 수정", notes = "VOD 정보를 수정하고 결과를 반환")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "vod", value = "v_id(vod 아이디), v_title(vod 이름), v_summary(vod 요약), v_director(vod 감독)" +
                    "v_actors(vod 출연진), v_age_grade(vod 연령등급), v_poster(vod 포스터 경로), gd_id(vod 디테일 장르)", dataType = "VodDTO", required = true)
    })
    @PutMapping("/vod_update")
    public int updateVod(@RequestBody VodDTO vod){
        return adminService.updateVod(vod);
    }





    @ApiOperation(value = "VOD 삭제", notes = "VOD 정보를 받아서 delete 후 결과 반환")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "v_id", value = "VOD 아이디", dataType = "int", required = true)
    })
    @DeleteMapping("/vod_delete")
    public int deleteVod(@RequestBody int v_id){
        return adminService.deleteVod(v_id);
    }






    @ApiOperation(value = "VOD 회차 업로드", notes = "VOD 회차 정보를 입력받아 회차를 업로드")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "episode", value = "v_id(vod 아이디), ve_episode_num(회차 번호), " +
                    "ve_contents(회차 영상 경로), ve_admin(회차 등록 관리자 닉네임)", dataType = "VodEpisodeDTO", required = true)
    })
    @PostMapping("/episode_upload")
    public int uploadEpisode(@RequestBody VodEpisodeDTO episode){
        return adminService.uploadEpisode(episode);
    }






    @ApiOperation(value = "VOD의 회차 목록 조회", notes = "VOD 하위의 모든 회차를 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "v_id", value = "VDO 아이디", dataType = "int", required = true)
    })
    @GetMapping("/episode_list")
    public List<VodEpisodeDTO> selectEpisode(int v_id){
        return adminService.selectEpisode(v_id);
    }





    @ApiOperation(value = "모든 회차 조회", notes = "모든 VOD의 모든 회차 조회")
    @GetMapping("/episode_all")
    public ResponseEntity<List<VodEpisodeAllDTO>> selectAllEpisode(){
        return new ResponseEntity<List<VodEpisodeAllDTO>>(adminService.selectAllEpisode(), HttpStatus.OK);
    }




    @ApiOperation(value = "VOD 회차 정보 수정", notes = "VOD 회차 정보를 수정하고 결과를 반환")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "episode", value = "ve_id(회차 아이디), ve_episode_num(회차 번호), ve_contents(회차 영상 경로)", dataType = "VodEpisodeDTO")
    })
    @PutMapping("/episode_update")
    public int updateVod(@RequestBody VodEpisodeDTO episode){
        return adminService.updateEpisode(episode);
    }






    @ApiOperation(value = "VOD 회차 삭제", notes = "VOD 회차 아이디로 delete 후 결과 반환")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ve_id", value = "VOD 회차 아이디", dataType = "int", required = true)
    })
    @DeleteMapping("/episode_delete")
    public int deleteEpisode(@RequestBody int ve_id){
        return adminService.deleteEpisode(ve_id);
    }






    @ApiOperation(value = "댓글 기능 차단당한 회원 리스트 조회", notes = "모든 댓글 기능 차단당한 회원 리스트 반환")
    @GetMapping("/blocked_member_list")
    public List<MemberDTO> selectBlockedMember(){
        return adminService.selectBlockedMember();
    }







    @ApiOperation(value = "사용자 댓글 기능 차단 설정 / 해제", notes = "사용자의 댓글 기능이 차단되어 있으면 해제, 해제되어 있으면 차단")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "u_id", value = "사용자 아이디(이메일 아님)", dataType = "integer", required = true)
    })
    @PutMapping("/member_block_unblock")
    public int updateCommentPermission(@RequestBody int u_id){
        return adminService.updateCommentPermission(u_id);
    }




    @ApiOperation(value = "신고당한 댓글 리스트 조회", notes = "모든 신고당한 댓글 리스트 반환")
    @GetMapping("/reported_member_list")
    public List<ReportListDTO> selectReportedCommentAndReComment(){
        return adminService.selectReportedCommentAndReComment();
    }



}
