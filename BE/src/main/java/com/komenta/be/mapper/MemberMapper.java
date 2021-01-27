package com.komenta.be.mapper;

import com.komenta.be.model.member.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    int joinMember(MemberDTO member);
    MemberDTO getMyInfo(String u_email);
    MemberDTO getInfoUser(int u_id);
    int updateMember(MemberDTO member);
    int deleteMember(int u_id);

}
