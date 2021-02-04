package com.komenta.be.controller;

import com.komenta.be.model.admin.ReportListDTO;
import com.komenta.be.model.member.MemberDTO;
import com.komenta.be.model.vod.VodDTO;
import com.komenta.be.model.vod.VodEpisodeAllDTO;
import com.komenta.be.model.vod.VodEpisodeDTO;
import com.komenta.be.service.AdminService;
import com.komenta.be.service.GenreService;
import com.komenta.be.service.JwtService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value ="/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    GenreService genreService;

    @Autowired
    JwtService jwtService;


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
    public ResponseEntity<Integer> updateMember(@RequestBody MemberDTO member, HttpServletResponse response){
        HttpStatus status = null;
        Map<String, Object> resultMap = new HashMap<>();
        System.out.println(member);
        int result = adminService.updateMember(member);
        status = HttpStatus.OK;

        return new ResponseEntity<Integer>(result, status);

    }


//    @ApiOperation(value = " VOD 업로드", notes = "VOD 정보와 VOD Episode 정보를 입력받고 VOD가 있으면 VOD EPISODE만 등록")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "vdto", value = "VDO 정보", dataType = "VodDTO", required = true),
//            @ApiImplicitParam(name = "vedto", value = "VDO Episode 정보", dataType = "VodEpisodeDTO", required = true)
//    })
//    @PostMapping("/vod_regist")
//    public int registVod(@RequestBody VodDTO vdto, @RequestBody VodEpisodeDTO vedto, @RequestParam("file") MultipartFile videofile, @RequestParam("v_poster") MultipartFile posterfile, HttpServletRequest request){
//        System.out.println(vdto);
//        System.out.println(vedto);
//        System.out.println(videofile);
//        System.out.println(posterfile);
//        System.out.println(request.getHeader("auth-token"));
//        int vodsuc = 0;
//        if(vdto.getV_id() == 0){
//            vdto.setV_poster(vdto.getV_title() + ".jpg");
//
//            vodsuc = adminService.registVod(vdto);
//            System.out.println("vodsuc : "+ vodsuc);
//            vedto.setV_id(vodsuc);
//            System.out.println("vod regist test "+vodsuc);
//
//            String poster = "C:/Users/multicampus/Desktop/Komenta/" + vdto.getV_title() + ".jpg";
////            String poster = "/home/ubuntu/Picture/Poster/" + vdto.getV_title() + ".jpg";
//            poster.replace(" ", "_");
//
//            File targetFile = new File(poster);
//            System.out.println(targetFile);
//
//            try {
//                InputStream fileStream = posterfile.getInputStream();
//                FileUtils.copyInputStreamToFile(fileStream, targetFile);
//                System.out.println("이미지 업로드 성공");
//            } catch (IOException e) {
//                FileUtils.deleteQuietly(targetFile);
//                e.printStackTrace();
//            }
//        }
//        else{
//            vedto.setV_id(vdto.getV_id());
//            vodsuc = 1;
//        }
//        String token = request.getHeader("auth-token");
//        String nickname = (String) jwtService.get(token).get("u_nickname");
//        vedto.setVe_admin(nickname);
//        int vodeip = adminService.uploadEpisode(vedto);
//        System.out.println("vod episode regist test"+vodeip);
//        /*
//         * 파일 업로드
//         */
//        if(vodeip == 1) {
//            // 100톰과제리50.mp4
//            String video = "C:/Users/multicampus/Desktop/Komenta/" + vedto.getVe_id() + vdto.getV_title() + vedto.getVe_episode_num() + ".mp4";
////            String video = "/home/ubuntu/Video/" + vedto.getVe_id() + vdto.getV_title() + vedto.getVe_episode_num() + ".mp4";
//
//
//            video.replace(" ", "_");
//
//            File targetFile = new File(video);
//            System.out.println(targetFile);
//
//            try {
//                InputStream fileStream = videofile.getInputStream();
//                FileUtils.copyInputStreamToFile(fileStream, targetFile);
//                System.out.println("파일 업로드 성공");
//            } catch (IOException e) {
//                FileUtils.deleteQuietly(targetFile);
//                e.printStackTrace();
//            }
//        }
//
//        return vodsuc + vodeip -1;
//    }


    @ApiOperation(value = " VOD 업로드", notes = "VOD 정보와 VOD Episode 정보를 입력받고 VOD가 있으면 VOD EPISODE만 등록")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "vod_all", value = "VDO episode까지의 정보", dataType = "VodEpisodeAllDTO", required = true),
    })
    @PostMapping("/vod_regist")
    public int registVod(@RequestBody VodEpisodeAllDTO vod_all, HttpServletRequest request) {
        System.out.println("여기 들어옴???");

        System.out.println(vod_all);
        VodDTO vod = new VodDTO();
        VodEpisodeDTO vodepi = new VodEpisodeDTO();
        int vod_id = vod_all.getV_id();
        System.out.println("vod_id는?"+vod_id);
        if(vod_id == 0){
            vod.setV_title(vod_all.getV_title());
            vod.setV_summary(vod_all.getV_summary());
            vod.setV_director(vod_all.getV_director());
            vod.setV_actors(vod_all.getV_actors());
            vod.setV_age_grade(vod_all.getV_age_grade());
            vod.setV_poster(vod_all.getV_poster());
            vod.setGd_id(vod_all.getGd_id());

            vod_id = adminService.registVod(vod);

        }
        vodepi.setV_id(vod_id);
        System.out.println(request.getHeader("auth-token"));
        String token = request.getHeader("auth-token");
        System.out.println("여기 토큰 들어가게 해줘 : "+token);
        String nickname = (String) jwtService.get(token).get("u_nickname");
        System.out.println(nickname);
        vodepi.setVe_admin(nickname);
        vodepi.setVe_episode_num(vod_all.getVe_episode_num());
        vodepi.setVe_contents(vod_all.getVe_contents());
        adminService.uploadEpisode(vodepi);
        System.out.println("vod episode regist test" + vodepi);

        return 1;
    }

    @ApiOperation(value = " Poster 업로드", notes = "vod,vod episode 등록과 같이 파일 등록")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "posterfile", value = "jpg file", dataType = "file", required = true),
    })
    @PostMapping("/poster_upload")
    public int registPoster(@RequestParam("v_poster") MultipartFile posterfile, HttpServletRequest request) {

//        String poster = "C:/Users/multicampus/Desktop/Komenta/" + posterfile.getOriginalFilename();
//        String poster = "C:/Users/multicampus/Desktop/Komenta/" + vod_all.getV_title() + ".jpg";
        String poster = "/home/ubuntu/Picture/Poster/" + posterfile.getOriginalFilename();
//        video.replace(" ", "_");

        File targetFile = new File(poster);
        System.out.println(targetFile);

        try {
            InputStream fileStream = posterfile.getInputStream();
            FileUtils.copyInputStreamToFile(fileStream, targetFile);
            System.out.println("그림 파일 업로드 성공");
        } catch (IOException e) {
            FileUtils.deleteQuietly(targetFile);
            e.printStackTrace();
        }
        return 1;
    }

    @ApiOperation(value = " File 업로드", notes = "vod,vod episode 등록과 같이 파일 등록")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "videofile", value = "video file", dataType = "file", required = true),
    })
    @PostMapping("/video_upload")
    public int registVideo(@RequestParam("file") MultipartFile videofile, HttpServletRequest request) {
        System.out.println(request.getHeader("auth-token"));
//        String video = "C:/Users/multicampus/Desktop/Komenta/" + videofile.getOriginalFilename();
//        String video = "C:/Users/multicampus/Desktop/Komenta/" + vod_all.getVe_id() + vod_all.getV_title() + vod_all.getVe_episode_num() + ".mp4";
            String video = "/home/ubuntu/Video/" + videofile.getOriginalFilename();
//        video.replace(" ", "_");

        File targetFile = new File(video);
        System.out.println(targetFile);

        try {
            InputStream fileStream = videofile.getInputStream();
            FileUtils.copyInputStreamToFile(fileStream, targetFile);
            System.out.println("파일 업로드 성공");
        } catch (IOException e) {
            FileUtils.deleteQuietly(targetFile);
            e.printStackTrace();
        }
        return 1;
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
        ResponseEntity<List<VodDTO>> dto =new ResponseEntity<List<VodDTO>>(adminService.selectVodByGd(gd_id), HttpStatus.OK);
        System.out.println(dto.getBody().get(0));
        return dto;
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
