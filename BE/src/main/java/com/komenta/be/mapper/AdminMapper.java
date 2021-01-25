package com.komenta.be.mapper;

import com.komenta.be.model.member.MemberDTO;
import com.komenta.be.model.vod.VodDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {

   public List<MemberDTO> selectAllMember();
   public int updateMember(String u_email);
   public int deleteMember(String u_email);

   public int uploadVod(VodDTO vod);
   public List<VodDTO> selectAllVod();
   public int updateVod(VodDTO vod);
   public int deleteVod(int v_id);
}
