package com.ams.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ams.entity.ActSign;
import com.ams.entity.Activity;
import com.ams.entity.User;

public interface ActSignMapper {
	int countByExample(@Param("str") String str,@Param("actid") int actid);
	List<User> selectByExample(@Param("str") String str,@Param("actid") int actid,@Param("offset") int offset, @Param("limit") int limit);
	int updateByExample(ActSign sign);
	int add(ActSign sign);
	ActSign selectOne(ActSign sign);
    int countByAct(@Param("actid") int actid);
    List<Activity> selectByUser(@Param("uid") int uid);
}
