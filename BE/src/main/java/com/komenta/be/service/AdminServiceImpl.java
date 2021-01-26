package com.komenta.be.service;

import com.komenta.be.model.member.MemberDTO;
import com.komenta.be.model.vod.VodDTO;
import com.komenta.be.model.vod.VodEpisodeDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{
    @Override
    public List<MemberDTO> selectAllMember() {
        return null;
    }

    @Override
    public int updateMember(String u_email) {
        return 0;
    }

    @Override
    public int deleteMember(String u_email) {
        return 0;
    }

    @Override
    public int registVod(VodDTO vod) {
        return 0;
    }

    @Override
    public List<VodDTO> selectAllVod() {
        return null;
    }

    @Override
    public int updateVod(VodDTO vod) {
        return 0;
    }

    @Override
    public int deleteVod(int v_id) {
        return 0;
    }

    @Override
    public int uploadEpisode(VodEpisodeDTO episode) {
        return 0;
    }

    @Override
    public List<VodEpisodeDTO> selectEpisode(int v_id) {
        return null;
    }

    @Override
    public int updateEpisode(VodEpisodeDTO episode) {
        return 0;
    }

    @Override
    public int deleteEpisode(int ve_id) {
        return 0;
    }
}
