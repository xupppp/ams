package com.ams.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ams.entity.Club;
import com.ams.entity.User;

public interface UserMapper {
	//xu
	User selectUserByLogin(User user);
	User selectUserByUid(int uid);
	User selectUserByEmid(String emid);
	List<User> selectByEntity(User user);
	int insertUser(User user);
	int updateByUid(@Param("user")User user);
	
	//xie
	List<User> selectUserForSearch(String s);
	int countByExample(@Param("str") String str);
	List<User> selectByUser(@Param("str") String str, @Param("offset") int offset, @Param("limit") int limit);
	int countByAdmin(@Param("str") String str);
	List<User> selectByAdmin(@Param("str") String str, @Param("offset") int offset, @Param("limit") int limit);
	User selectBypassword(@Param("pwd") String pwd,@Param("uid") int uid);
	
	//yu
	List<User> selectByNotPre(User user);
	List<User> selectByNotIn(User user);
	int countuserSum();
	int countadminSum();

}
