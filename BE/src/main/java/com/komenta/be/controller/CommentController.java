package com.komenta.be.controller;

import com.komenta.be.model.comment.*;
import com.komenta.be.model.member.AuthPhoneDTO;
import com.komenta.be.model.member.MemberDTO;
import com.komenta.be.service.CommentService;
import com.komenta.be.service.JwtService;
import com.komenta.be.service.MemberService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

//거기서 댓글 등록하면 댓글 입력
//그 회차의 모든 댓글 리스트


@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value ="/comment")
public class CommentController {

    @Autowired
    CommentService cservice;
    @Autowired
    JwtService jwtService;

    @ApiOperation(value = "댓글 입력", notes = "댓글을 입력하면 해당 댓글을 DB에 저장하고 이를 반환")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "comment_info", value = "c_contents(댓글 내용), u_playtime(등록 시간), u_id(회원 아이디), ve_id(vod 회차 아이디)", dataType = "CommentInfoDTO", required = true)
    })
    @PostMapping("/insert")
    public int insertComment(@RequestBody CommentInfoDTO comment_info){
        return cservice.insertComment(comment_info);
    }




    @ApiOperation(value = "회차별 댓글 정보", notes = "해당 회차의 모든 댓글 정보를 반환")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ve_id", value = "회차 아이디", dataType = "int",required = true)
    })
    @GetMapping("/ve_c_list/{ve_id}")
    public List<VodEpisodeCommentDTO> getVodEpisodeComment(@PathVariable("ve_id") int ve_id, HttpServletRequest request){
        int u_id = jwtService.getUidFromJwt(request.getHeader("auth-token"));
        CommentListByVeIdDTO comment_info = new CommentListByVeIdDTO(ve_id, u_id);
        return cservice.getVodEpisodeComment(comment_info);
    }



    @ApiOperation(value = "실시간 베스트 댓글 유저 랭킹", notes = "현재 가장 많은 좋아요를 받은 댓글 랭킹 10위")
    @GetMapping("/comment_rank")
    public List<CommentRankDTO> getCommentRankList(){
        return cservice.getCommentRankList();
    }


    @ApiOperation(value = "회원이 단 모든 댓글 조회", notes = "입력받은 u_id가 달았던 모든 댓글 조회")
    @GetMapping("/comment_list")
    public List<MyCommentDTO> getMyComment(int u_id, HttpServletRequest request){
        return cservice.getMyComment(u_id);
    }


    @ApiOperation(value = "회원이 단 모든 댓글 삭제", notes = "c_id 입력 받고 해당 댓글 삭제")
    @DeleteMapping("/comment_delete")
    public int deleteMyComment(@RequestBody int c_id, HttpServletRequest request){
        int u_id= jwtService.getUidFromJwt(request.getHeader("auth-token"));
        CommentInfoDTO dto = new CommentInfoDTO();
        dto.setU_id(u_id);
        dto.setC_id(c_id);
        return cservice.deleteMyComment(dto);
    }

    @ApiOperation(value = "댓글 좋아요 추가/취소", notes = "해당 유저가 좋아요한 댓글은 취소, 아닌 댓글은 좋아요 추가")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "comment_good", value = "c_id (댓글 아이디)", dataType = "CommentGoodDTO", required = true)
    })
    @PostMapping("/comment_good_cancel")
    public int cancelLikeComment(@RequestBody CommentGoodDTO comment_good, HttpServletRequest request){
        // DTO 채워준다. (u_id)
        int u_id = jwtService.getUidFromJwt(request.getHeader("auth-token"));
        comment_good.setU_id(u_id);

        // 만약에 내가 이 댓글을 좋아요한 상태가 아니면 좋아요 추가하고 리턴
        // 좋아요 한 상태면 좋아요 삭제하고 리턴
        if(cservice.isCommentGood(comment_good) == 0) {
            return cservice.addLikeComment(comment_good);
        }
        return cservice.cancelLikeComment(comment_good);
    }
}