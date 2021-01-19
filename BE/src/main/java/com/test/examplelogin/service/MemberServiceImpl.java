package com.test.examplelogin.service;

import com.test.examplelogin.mapper.MemberMapper;
import com.test.examplelogin.model.MemberDTO;
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