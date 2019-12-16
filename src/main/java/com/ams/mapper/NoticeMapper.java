package com.ams.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ams.dto.NoticeDTO;
import com.ams.entity.Notice;
import com.ams.entity.User;

public interface NoticeMapper {
	int countByExample(@Param("str") String str);
	List<Notice> selectByExample(@Param("str") String str,@Param("offset") int offset, @Param("limit") int limit);
	int updateByExample(Notice notice);
	int insertByExample(Notice notice);
	List<Notice> listNotice(Notice notice);
	User selectCharge(@Param("nid") int nid);
	NoticeDTO selectOne(@Param("type")int type,@Param("nid")int nid);
}