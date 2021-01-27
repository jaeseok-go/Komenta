package com.komenta.be.service;

import com.komenta.be.mapper.MemberMapper;
import com.komenta.be.model.member.MemberDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    SqlSession sqlsession;

    @Override
    public int joinMember(MemberDTO dto) {
        return sqlsession.getMapper(MemberMapper.class).joinMember(dto);
    }

    @Override
    public MemberDTO getMyInfo(String u_email) {
        return sqlsession.getMapper(MemberMapper.class).getInfoUser(u_email);
    }

    @Override
    MemberDTO getInfoUser(int u_id) {
        return sqlsession.getMapper(MemberMapper.class).getInfoAnotherUser(u_id);
    }

    @Override
    public int updateMember(MemberDTO dto) {
        return sqlsession.getMapper(MemberMapper.class).updateMember(dto);
    }

    @Override
    public int deleteMember(int u_id) {
        return sqlsession.getMapper(MemberMapper.class).deleteMember(u_id);
    }
}