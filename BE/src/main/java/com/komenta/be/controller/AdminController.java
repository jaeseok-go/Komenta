package com.komenta.be.controller;

import com.komenta.be.model.member.MemberDTO;
import com.komenta.be.model.vod.VodDTO;
import com.komenta.be.service.AdminService;
import com.komenta.be.service.MemberService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping(value ="/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @ApiOperation(value = "회원목록 조회", notes = "모든 회원 정보를 리스트로 반환")
    @GetMapping("/member_list")
    public List<MemberDTO> selectAllMembers(){
        return adminService.selectAllMember();
    }



    @ApiOperation(value = "회원정보 수정", notes = "회원 정보를 받아서 update 후 결과 반환")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "u_email", value = "회원 아이디", dataType = "String", required = true)
    })
    @PutMapping("/member_update")
    public int updateMember(String u_email){
        return adminService.updateMember(u_email);
    }



    @ApiOperation(value = "회원정보 삭제", notes = "회원 정보를 받아서 delete 후 결과 반환")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "u_email", value = "회원 아이디", dataType = "String", required = true)
    })
    @DeleteMapping("/member_delete")
    public int deleteMember(String u_email){
        return adminService.deleteMember(u_email);
    }



    @ApiOperation(value = "VOD 업로드", notes = "VOD 정보를 입력받아 스트리밍 서버에 업로드")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "vod", value = "VDO 정보", dataType = "VodDTO", required = true)
    })
    @PostMapping("/vod_upload")
    public int uploadVod(VodDTO vod, @RequestParam("file") MultipartFile multipartFile){
        System.out.println(vod);
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
        return adminService.uploadVod(vod);
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
        return adminService.uploadVod(vod);
    }


    @ApiOperation(value = "VOD 목록 조회", notes = "모든 VOD 정보를 조회")
    @GetMapping("/vod_list")
    public List<VodDTO> selectAllVod(){
        return adminService.selectAllVod();
    }



    @ApiOperation(value = "VOD 정보 수정", notes = "VOD 정보를 수정하고 결과를 반환")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "vod", value = "VOD 정보", dataType = "VodDTO", required = true)
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
}
