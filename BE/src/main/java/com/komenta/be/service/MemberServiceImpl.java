package com.komenta.be.service;

import com.komenta.be.mapper.MemberMapper;
import com.komenta.be.model.member.MemberDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService{

    @Autowired
    SqlSession sqlsession;

    @Override
    public MemberDTO getPw(String userid) {
        return sqlsession.getMapper(MemberMapper.class).getPw(userid);
    }
}