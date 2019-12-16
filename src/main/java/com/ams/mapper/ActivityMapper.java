package com.ams.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ams.dto.ActivityDTO;
import com.ams.entity.Activity;
import com.ams.entity.Club;
import com.ams.entity.User;


public interface ActivityMapper {
	int countByExample(@Param("str") String str,@Param("type") int type,@Param("state") String state,@Param("act_type") String act_type);
	//分页
	List<Activity> selectByExample(@Param("str") String str,@Param("type") int type,@Param("state") String state,@Param("act_type") String act_type,@Param("offset") int offset, @Param("limit") int limit);
	List<Activity> selectByExample2(@Param("str") String str,@Param("type") int type,@Param("state") String state,@Param("act_type") String act_type,@Param("atid") int atid);
	ActivityDTO selectOne(@Param("type")int type,@Param("actid")int actid);
	List<Activity> listAct(Activity act);
	int updateByExample(Activity act);
	User selectCharge(@Param("actid") int actid);
	int insertByExample(Activity act);
	List<Activity> selectHot();
	
	List<Club> selectTop();
	int countactSum();
	 int countnewactSum();
	 int countcheckAct();
	 List<Club> selectActTop();
	 
	 int countByAct(@Param("str") String str,@Param("atid") Integer atid);
		List<Activity> selectByAct(@Param("str") String str, @Param("atid") Integer atid, @Param("offset") int offset, @Param("limit") int limit);
}
