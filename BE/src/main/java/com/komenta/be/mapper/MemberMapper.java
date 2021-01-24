package com.komenta.be.mapper;

import com.komenta.be.model.member.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    int joinMember(MemberDTO dto);
    MemberDTO getPw(String userid);
    int updateMember(MemberDTO dto);
}
