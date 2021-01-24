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
    public MemberDTO getInfoUser(String useremail) {
        return sqlsession.getMapper(MemberMapper.class).getInfoUser(useremail);
    }

    @Override
    public int updateMember(MemberDTO dto) {
        return sqlsession.getMapper(MemberMapper.class).updateMember(dto);
    }

    @Override
    public int deleteMember(int uid) {
        return sqlsession.getMapper(MemberMapper.class).deleteMember(uid);
    }
}