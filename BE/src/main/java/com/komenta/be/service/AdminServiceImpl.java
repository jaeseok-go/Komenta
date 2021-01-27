package com.komenta.be.service;

import com.komenta.be.mapper.AdminMapper;
import com.komenta.be.model.admin.ReportListDTO;
import com.komenta.be.model.member.MemberDTO;
import com.komenta.be.model.vod.VodDTO;
import com.komenta.be.model.vod.VodEpisodeDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{
    SqlSession sqlSession;
    @Override
    public List<MemberDTO> selectAllMember() {
        List<MemberDTO> dto = sqlSession.getMapper(AdminMapper.class).selectAllMember();
        return dto;
    }

    @Override
    public int updateMember(MemberDTO member) {
        return sqlSession.getMapper(AdminMapper.class).updateMember(member);
    }

    @Override
    public int deleteMember(String u_email) {
        return sqlSession.getMapper(AdminMapper.class).deleteMember(u_email);
    }

    @Override
    public int registVod(VodDTO vod) {
        return sqlSession.getMapper(AdminMapper.class).registVod(vod);
    }

    @Override
    public List<VodDTO> selectAllVod() {
        return sqlSession.getMapper(AdminMapper.class).selectAllVod();
    }

    @Override
    public int updateVod(VodDTO vod) {
        return sqlSession.getMapper(AdminMapper.class).updateVod(vod);
    }

    @Override
    public int deleteVod(int v_id) {
        return sqlSession.getMapper(AdminMapper.class).deleteVod(v_id);
    }

    @Override
    public int uploadEpisode(VodEpisodeDTO episode) {
        return sqlSession.getMapper(AdminMapper.class).uploadEpisode(episode);
    }

    @Override
    public List<VodEpisodeDTO> selectEpisode(int v_id) {
        return sqlSession.getMapper(AdminMapper.class).selectEpisode(v_id);
    }

    @Override
    public int updateEpisode(VodEpisodeDTO episode) {
        return sqlSession.getMapper(AdminMapper.class).updateEpisode(episode);
    }

    @Override
    public int deleteEpisode(int ve_id) {
        return sqlSession.getMapper(AdminMapper.class).deleteEpisode(ve_id);
    }

    @Override
    public int updateCommentPermission(int u_id) {
        return sqlSession.getMapper(AdminMapper.class).updateCommentPermission(u_id);
    }

    @Override
    public List<MemberDTO> selectBlockedMember() {
        return sqlSession.getMapper(AdminMapper.class).selectBlockedMember();
    }

    @Override
    public List<ReportListDTO> selectReportedCommentAndReComment() {
        return sqlSession.getMapper(AdminMapper.class).selectReportedCommentAndReComment();
    }
}
