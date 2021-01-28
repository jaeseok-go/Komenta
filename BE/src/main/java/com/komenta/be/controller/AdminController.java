package com.komenta.be.controller;

import com.komenta.be.model.admin.ReportListDTO;
import com.komenta.be.model.genre.GenreDTO;
import com.komenta.be.model.member.MemberDTO;
import com.komenta.be.model.vod.VodDTO;
import com.komenta.be.model.vod.VodEpisodeDTO;
import com.komenta.be.service.AdminService;
import com.komenta.be.service.GenreService;
import com.komenta.be.service.MemberService;
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
            @ApiImplicitParam(name = "u_email", value = "회원 이메일(아이디 아님)", dataType = "String", required = true),
            @ApiImplicitParam(name = "u_expire_member", value = "멤버쉽 종료일자", dataType = "String", required = true),
            @ApiImplicitParam(name = "u_id", value = "회원 아이디", dataType = "Integer", required = true),
            @ApiImplicitParam(name = "u_is_admin", value = "회원 관리자 여부", dataType = "boolean", required = true),
            @ApiImplicitParam(name = "u_is_blocked", value = "회원 댓글기능 차단 여부", dataType = "boolean", required = true),
            @ApiImplicitParam(name = "u_nickname", value = "회원 닉네임", dataType = "String", required = true),
            @ApiImplicitParam(name = "u_phone_number", value = "회원 휴대전화 번호", dataType = "String", required = true),
            @ApiImplicitParam(name = "u_profile_pic", value = "회원 프로필 사진 경로", dataType = "String", required = true),
            @ApiImplicitParam(name = "u_pw", value = "회원 비밀번호", dataType = "String", required = true)
    })
    @PutMapping("/member_update")
    public int updateMember(MemberDTO member){
        return adminService.updateMember(member);
    }



    @ApiOperation(value = "회원정보 삭제", notes = "회원 정보를 받아서 delete 후 결과 반환")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "u_email", value = "회원 아이디", dataType = "String", required = true)
    })
    @DeleteMapping("/member_delete")
    public int deleteMember(String u_email){
        return adminService.deleteMember(u_email);
    }

    @ApiOperation(value = "정보가 없던 VOD 업로드(test용)", notes = "VOD 정보를 입력받아 VOD 회차를 업로드할 수 있는 VOD 등록")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "vod", value = "VDO 정보", dataType = "VodDTO", required = true),
            @ApiImplicitParam(name = "vod", value = "VDO Episode 정보", dataType = "VodEpisodeDTO", required = true)


    })
    @PostMapping("/vod_regist_first")
    public int registVodFirst(VodDTO vdto, VodEpisodeDTO vedto, @RequestParam("file") MultipartFile multipartFile){
        int a = adminService.registVod(vdto);
        int b = adminService.uploadEpisode(vedto);
        /*
        * 파일 업로드
         */
        File targetFile = new File("resources/vod" + multipartFile.getOriginalFilename());
        System.out.println(targetFile);
        try {
            InputStream fileStream = multipartFile.getInputStream();
            FileUtils.copyInputStreamToFile(fileStream, targetFile);
            System.out.println("성공");
        } catch (IOException e) {
            FileUtils.deleteQuietly(targetFile);
            e.printStackTrace();
        }

        return -1+a+b;
    }
    @ApiOperation(value = "VOD 업로드(test용)", notes = "VOD 정보를 입력받아 VOD 회차를 업로드할 수 있는 VOD 등록")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "vod", value = "VDO 정보", dataType = "VodDTO", required = true)
    })
    @PostMapping("/vod_regist")
    public int registVod(@RequestBody VodDTO vdto, @RequestParam("file") MultipartFile multipartFile){
        int result = adminService.registVod(vdto);
        System.out.println("result :"+result);
        /*
        * VOD 파일 업로드
         */
        File targetFile = new File("resources/vod" + multipartFile.getOriginalFilename());
        System.out.println(targetFile);
        try {
            InputStream fileStream = multipartFile.getInputStream();
            FileUtils.copyInputStreamToFile(fileStream, targetFile);
            System.out.println("성공");
        } catch (IOException e) {
            FileUtils.deleteQuietly(targetFile);
            e.printStackTrace();
        }
        return result;
    }

    @PostMapping("/vod_uploadtest")
    public int uploadVodtest(@RequestParam("v_title") String v_title, @RequestParam("v_summary") String v_summary,@RequestParam("v_director") String v_director,@RequestParam("v_actors") String v_actors,@RequestParam("v_age_grade") String v_age_grade,@RequestParam("v_poster") String v_poster,@RequestParam("gd_id") String gd_id, @RequestParam("file") MultipartFile multipartFile){
        System.out.println(v_title);
        File targetFile = new File("C:/Users/multicampus/Desktop/Komenta/s04p12b201/BE/src/main/resources/vod/" + multipartFile.getOriginalFilename());
        System.out.println(targetFile);
        try {
            InputStream fileStream = multipartFile.getInputStream();
            FileUtils.copyInputStreamToFile(fileStream, targetFile);
            System.out.println("성공");
        } catch (IOException e) {
            FileUtils.deleteQuietly(targetFile);
            e.printStackTrace();
        }
        VodDTO vod = new VodDTO(v_title, v_summary, v_director, v_actors, Integer.parseInt(v_age_grade),v_poster,  Integer.parseInt(gd_id));
        return adminService.registVod(vod);
    }


    @ApiOperation(value = "VOD 목록 조회", notes = "모든 VOD 정보를 조회")
    @GetMapping("/vod_list")
    public ResponseEntity<List<VodDTO>> selectAllVod(){
        List<VodDTO> result = adminService.selectAllVod();
        for(VodDTO dto:result) {
            System.out.println(dto);
        }
        return new ResponseEntity<List<VodDTO>>(result, HttpStatus.OK);
    }

    @GetMapping("/vod_list_by_gdid/{gd_id}")
    public ResponseEntity<List<VodDTO>> selectVodByGd(@PathVariable int gd_id){

        List<VodDTO> result = adminService.selectVodByGd(gd_id);
        for(VodDTO dto:result) {
            System.out.println(dto);
        }

        return new ResponseEntity<List<VodDTO>>(result, HttpStatus.OK);
    }
    @ApiOperation(value = "VOD 정보 수정", notes = "VOD 정보를 수정하고 결과를 반환")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "gd_id", value = "세부 장르 아이디", dataType = "Integer", required = true),
            @ApiImplicitParam(name = "v_actors", value = "출연자들 이름", dataType = "String", required = true),
            @ApiImplicitParam(name = "v_age_grade", value = "연령 등급", dataType = "Integer", required = true),
            @ApiImplicitParam(name = "v_director", value = "감독", dataType = "String", required = true),
            @ApiImplicitParam(name = "v_id", value = "VOD 아이디", dataType = "Integer", required = true),
            @ApiImplicitParam(name = "v_poster", value = "VOD 포스터 경로", dataType = "String", required = true),
            @ApiImplicitParam(name = "v_summary", value = "VOD 요약", dataType = "String", required = true),
            @ApiImplicitParam(name = "v_title", value = "VOD 제목", dataType = "String", required = true)
    })
    @PutMapping("/vod_update")
    public int updateVod(VodDTO vod){
        return adminService.updateVod(vod);
    }



    @ApiOperation(value = "VOD 삭제", notes = "VOD 정보를 받아서 delete 후 결과 반환")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "v_id", value = "VOD 아이디", dataType = "Integer", required = true)
    })
    @DeleteMapping("/vod_delete")
    public int deleteVod(int v_id){
        return adminService.deleteVod(v_id);
    }



    @ApiOperation(value = "VOD 회차 업로드", notes = "VOD 회차 정보를 입력받아 회차를 업로드")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "v_id", value = "VOD 회차가 포함된 VOD 아이디", dataType = "Integer"),
            @ApiImplicitParam(name = "ve_contents", value = "VOD 회차 영상 URL", dataType = "String", required = true),
            @ApiImplicitParam(name = "ve_episode_num", value = "VOD 회차 번호", dataType = "Integer", required = true),
            @ApiImplicitParam(name = "ve_id", value = "VOD 회차 아이디", dataType = "Integer", required = true)
    })
    @PostMapping("/episode_upload")
    public int uploadEpisode(@RequestBody VodEpisodeDTO episode){
        return adminService.uploadEpisode(episode);
    }

    @ApiOperation(value = "VOD 회차 목록 조회", notes = "VOD 하위의 모든 회차를 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "v_id", value = "VDO 아이디", dataType = "Integer", required = true)
    })
    @GetMapping("/episode_list")
    public List<VodEpisodeDTO> selectEpisode(int v_id){
        return adminService.selectEpisode(v_id);
    }

    @GetMapping("/episode_all")
    public ResponseEntity<List<VodEpisodeDTO>> selectAllEpisode(){
        List<VodEpisodeDTO> result = adminService.selectAllEpisode();
        for(VodEpisodeDTO dto:result) {
            System.out.println(dto);
        }

        return new ResponseEntity<List<VodEpisodeDTO>>(result, HttpStatus.OK);
    }


    @ApiOperation(value = "VOD 회차 정보 수정", notes = "VOD 회차 정보를 수정하고 결과를 반환")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "v_id", value = "VOD 회차가 포함된 VOD 아이디(null로 줘도 됨)", dataType = "Integer"),
            @ApiImplicitParam(name = "ve_contents", value = "VOD 회차 영상 URL", dataType = "String", required = true),
            @ApiImplicitParam(name = "ve_episode_num", value = "VOD 회차 번호", dataType = "Integer", required = true),
            @ApiImplicitParam(name = "ve_id", value = "VOD 회차 아이디", dataType = "Integer", required = true)
    })
    @PutMapping("/episode_update")
    public int updateVod(VodEpisodeDTO episode){
        return adminService.updateEpisode(episode);
    }




    @ApiOperation(value = "VOD 회차 삭제", notes = "VOD 회차 아이디로 delete 후 결과 반환")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ve_id", value = "VOD 회차 아이디", dataType = "Integer", required = true)
    })
    @DeleteMapping("/episode_delete")
    public int deleteEpisode(int ve_id){
        return adminService.deleteEpisode(ve_id);
    }




    @ApiOperation(value = "댓글 기능 차단 당한 회원 리스트 조회", notes = "모든 차단당한 회원 리스트 반환")
    @GetMapping("/blocked_member_list")
    public List<MemberDTO> selectBlockedMember(){
        return adminService.selectBlockedMember();
    }





    @ApiOperation(value = "사용자 댓글 기능 차단 설정 / 해제", notes = "사용자의 댓글 기능이 차단되어 있으면 해제, 해제되어 있으면 차단")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "u_id", value = "사용자 아이디(이메일 아님)", dataType = "integer", required = true)
    })
    @PutMapping("/member_block_unblock")
    public int updateCommentPermission(int u_id){
        return adminService.updateCommentPermission(u_id);
    }

    @ApiOperation(value = "신고당한 댓글 리스트 조회", notes = "모든 신고당한 댓글 리스트 반환")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "r_id", value = "신고 내역 아이디", dataType = "integer", required = true),
            @ApiImplicitParam(name = "r_type", value = "신고 내역 타입", dataType = "String", required = true),
            @ApiImplicitParam(name = "r_contents", value = "신고 내용", dataType = "String", required = true),
            @ApiImplicitParam(name = "cu_id", value = "신고한 사용자 아이디", dataType = "integer", required = true),
            @ApiImplicitParam(name = "u_id", value = "댓글이나 대댓글 남긴 사용자 아이디", dataType = "integer", required = true),
            @ApiImplicitParam(name = "u_email", value = "신고당한 사용자 이메일", dataType = "String", required = true),
            @ApiImplicitParam(name = "u_pw", value = "신고당한 사용자 비밀번호", dataType = "String", required = true),
            @ApiImplicitParam(name = "u_phone_number", value = "신고당한 사용자 전화번호", dataType = "String", required = true),
            @ApiImplicitParam(name = "u_nickname", value = "신고당한 사용자 닉네임", dataType = "String", required = true),
            @ApiImplicitParam(name = "u_expire_member", value = "신고당한 사용자 멤버쉽 만료일", dataType = "String", required = true),
            @ApiImplicitParam(name = "u_is_admin", value = "신고당한 사용자 관리자 여부", dataType = "boolean", required = true),
            @ApiImplicitParam(name = "u_is_blocked", value = "신고당한 사용자 댓글 기능 차단 여부", dataType = "boolean", required = true),
            @ApiImplicitParam(name = "u_profil_pic", value = "신고당한 사용자 프로필 파일", dataType = "String", required = true)

    })
    @GetMapping("/reported_member_list")
    public List<ReportListDTO> selectReportedCommentAndReComment(){
        return adminService.selectReportedCommentAndReComment();
    }



}
