package com.ams.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ams.entity.Club;
import com.ams.entity.ClubMembers;

public interface ClubMembersMapper {
	List<ClubMembers> listByUid(@Param("uid")int uid);
	int countByMember(@Param("cid") int cid);
	int countByExample(@Param("str") String str,@Param("cid") int cid);
	List<ClubMembers> selectByExample(@Param("str") String str,@Param("cid") int cid,@Param("offset") int offset, @Param("limit") int limit);
	int updateByExample(ClubMembers clubMembers);
	int add(ClubMembers clubmembers);
	int addstu(ClubMembers clubmembers);
	int addApply(ClubMembers clubmembers);
	int addclub(ClubMembers clubmembers);
	ClubMembers selectOne(ClubMembers clubmembers);
	ClubMembers selectApplyOne(ClubMembers clubmembers);
	ClubMembers selectDeleteOne(ClubMembers clubmembers);
	ClubMembers selectExistOne(ClubMembers clubmembers);
	List<Club> selectTop();
	List<Club> selectMemberTop();
	List<Club> selectActTop();
	int insertByExample(ClubMembers clubmembers);
	int countByCid(@Param("str") String str,@Param("cid") int cid);
	List<ClubMembers> selectByCid(@Param("str") String str,@Param("cid") int cid,@Param("offset") int offset, @Param("limit") int limit);
	int countByStu(@Param("str") String str,@Param("cid") int cid);
	List<ClubMembers> selectByStu(@Param("str") String str,@Param("cid") int cid,@Param("offset") int offset, @Param("limit") int limit);
	ClubMembers selectReason(@Param("uid") int uid,@Param("cid") int cid);
}
