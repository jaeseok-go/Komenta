package com.komenta.be.service;

import com.komenta.be.model.member.MemberDTO;

public interface MemberService{

    public int joinMember(MemberDTO dto);
    public MemberDTO getInfoUser(String useremail);
    public int updateMember(MemberDTO dto);
    public int deleteMember(int uid);
}