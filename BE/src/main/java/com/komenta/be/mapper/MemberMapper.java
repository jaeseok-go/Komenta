package com.komenta.be.mapper;

import com.komenta.be.model.member.MemberDTO;
import com.komenta.be.model.member.MemberSearchInfoDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    int joinMember(MemberDTO member);
    MemberDTO getMyInfo(String u_email);
    MemberDTO getInfoUser(int u_id);
    String findId(String u_phone_number);
    String findPw(String u_email);
    int updatePassword(MemberDTO member);
    int updateMember(MemberDTO member);
    int deleteMember(int u_id);
    String chkDupByID(String u_email);
    String chkDupByNickname(String u_nickname);
    int updateMembership(int u_id);
    List<MemberSearchInfoDTO> getAllMember();
}
