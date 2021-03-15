package com.komenta.be.controller;

import com.komenta.be.model.follow.FollowDTO;
import com.komenta.be.model.follow.FollowSelectDTO;
import com.komenta.be.model.follow.UnFollowDTO;
import com.komenta.be.service.FollowService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value ="/follow")
public class FollowController {

    @Autowired
    FollowService followService;



    @ApiOperation(value = "팔로잉 조회", notes = "입력받은 u_id가 팔로우한 팔로잉 리스트를 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "u_id", value = "회원 아이디(이메일 아님)", dataType = "int", required = true)
    })
    @GetMapping("/following_list")
    public List<FollowSelectDTO> getFollowingList(int u_id){
        return followService.getFollowingList(u_id);
    }





    @ApiOperation(value = "언팔로잉 조회", notes = "입력받은 u_id가 언팔로우한 언팔로잉 리스트를 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "u_id", value = "회원 아이디(이메일 아님)", dataType = "int", required = true)
    })
    @GetMapping("/unfollowing_list")
    public List<FollowSelectDTO> getUnFollowingList(int u_id){
        return followService.getUnFollowingList(u_id);
    }





    @ApiOperation(value = "팔로워 조회", notes = "입력받은 u_id를 팔로우한 팔로워 리스트를 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "u_id", value = "회원 아이디(이메일 아님)", dataType = "int", required = true)
    })
    @GetMapping("/follower_list")
    public List<FollowSelectDTO> getFollowerList(int u_id){
        return followService.getFollowerList(u_id);
    }





    @ApiOperation(value = "팔로우 추가/취소", notes = "입력받은 u_id가 f_id를 팔로우/팔로우취소하게 처리한 후 결과값을 반환")
   @ApiImplicitParams({
            @ApiImplicitParam(name = "follow_info", value = "u_id(팔로우의 주체), f_id(팔로우의 대상)", dataType = "FollowDTO", required = true)
    })
    @PostMapping("/add_sub")
    public int followAddSub(@RequestBody FollowDTO follow_info){
        if(followService.isFollower(follow_info).size() > 0) {
            return followService.followSub(follow_info);
        }
        return followService.followAdd(follow_info);
    }





    @ApiOperation(value = "언팔로우 추가/취소", notes = "입력받은 u_id가 f_id를 언팔로우/언팔로우취소하게 처리한 후 결과값을 반환")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "unfollow_info", value = "u_id(언팔로우의 주체), uf_id(언팔로우의 대상)", dataType = "UnFollowDTO", required = true)
    })
    @PostMapping("/un_add_sub")
    public int unFollowAddSub(@RequestBody UnFollowDTO unfollow_info){
        if(followService.isUnFollower(unfollow_info).size() > 0) {
            return followService.unFollowSub(unfollow_info);
        }
        return followService.unFollowAdd(unfollow_info);
    }


}
