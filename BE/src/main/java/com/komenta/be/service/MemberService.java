package com.komenta.be.service;

import com.komenta.be.model.member.MemberDTO;
import com.komenta.be.model.member.MemberSearchInfoDTO;

import java.util.List;

public interface MemberService{

    // 회원가입
    public int joinMember(MemberDTO member);

    // 내 정보 조회
    public MemberDTO getMyInfo(String u_email);

    // 다른 사람 정보 조회
    public MemberDTO getInfoUser(int u_id);

    // 아이디 찾기
    public String findId(String u_phone_number);

    // 비밀번호 찾기
    public String findPw(String u_email);

    // 비밀번호 변경
    public int updatePassword(MemberDTO member);

    // 회원 정보 수정
    public int updateMember(MemberDTO member);

    //회원 정보 삭제
    public int deleteMember(int u_id);

    //회원 이메일 중복 확인
    public String chkDupByID(String u_email);

    //회원 닉네임 중복 확인
    public String chkDupByNickname(String u_nickname);

    //회원 멤버쉽 가입/해지
    public int updateMembership(int u_id);

    public List<MemberSearchInfoDTO> getAllMember();

}