package com.ams.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ams.dto.ClubDTO;
import com.ams.entity.Club;
import com.ams.entity.User;

public interface ClubMapper {
	List<Club> listAll();
	Club selectManagerClub(@Param("uid") int uid);
	int countclubSum();
	int countnewclubSum();
	int countcheckClub();
	int countByExample(@Param("str") String str);
	List<Club> selectByExample(@Param("str") String str,@Param("offset") int offset, @Param("limit") int limit);
	int countRun(@Param("str") String str,@Param("ctid") int ctid);
	List<Club> selectRun(@Param("str") String str,@Param("offset") int offset, @Param("limit") int limit,@Param("ctid") int ctid);
	int updateByExample(Club club);
	User selectPresident(@Param("cid") int cid);
	User selectUser(@Param("uid") int uid);
	Club selectMore(@Param("cid") int cid);
	int insertByExample(Club club);
	int countByNew(@Param("str") String str);
	List<Club> selectByNew(@Param("str") String str,@Param("offset") int offset, @Param("limit") int limit);
	int countByModify(@Param("str") String str);
	List<Club> selectByModify(@Param("str") String str,@Param("offset") int offset, @Param("limit") int limit);
	ClubDTO selectOne(@Param("cid") int cid);
	List<Club> listClub(Club club);
	Club modifyManagerClub(@Param("cid") int cid);
	List<User> listUser(@Param("cid") int cid);
	int updatePresident(@Param("cid") int cid);
	int updatePresident2(@Param("cid") int cid, @Param("uid") int uid);
	List<Club> selectByUid(@Param("uid") int uid);
	int updateByExampleMember(Club club);
	int countByDel(@Param("str") String str);
	List<Club> selectByDel(@Param("str") String str,@Param("offset") int offset, @Param("limit") int limit);
	int updateByExampleMemberBack(Club club);
	int updateByDismiss(@Param("cid") int cid);
	int updateByDismiss2(@Param("cid") int cid);
	
	int countByClub(@Param("str") String str,@Param("ctid") Integer ctid);
	List<Club> selectByClub(@Param("str") String str, @Param("ctid") Integer ctid, @Param("offset") int offset, @Param("limit") int limit);
	
}
