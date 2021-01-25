package com.komenta.be.service;

import com.komenta.be.model.member.MemberDTO;
import com.komenta.be.model.vod.VodDTO;

import java.util.List;

public interface AdminService {

    public List<MemberDTO> selectAllMember();
    public int updateMember(String u_email);
    public int deleteMember(String u_email);

    public int uploadVod(VodDTO vod);
    public List<VodDTO> selectAllVod();
    public int updateVod(VodDTO vod);
    public int deleteVod(int v_id);

}
