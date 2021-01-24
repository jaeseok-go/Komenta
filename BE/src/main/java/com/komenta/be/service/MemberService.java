package com.komenta.be.service;

import com.komenta.be.model.member.MemberDTO;

public interface MemberService{

    public int joinMember(MemberDTO dto);
    public MemberDTO getPw(String userid);

}