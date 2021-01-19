package com.test.examplelogin.mapper;

import com.test.examplelogin.model.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    MemberDTO getPw(String userid);
}
