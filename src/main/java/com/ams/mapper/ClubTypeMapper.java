package com.ams.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ams.dto.ActivityDTO;
import com.ams.entity.Activity;
import com.ams.entity.ActivityType;
import com.ams.entity.Club;
import com.ams.entity.ClubType;
import com.ams.entity.User;


public interface ClubTypeMapper {
    List<ClubType> list();
    int countByClubType(@Param("str") String str);
    List<ClubType> selectByClubType(@Param("str") String str, @Param("offset") int offset, @Param("limit") int limit);
    int updateByCtid(@Param("clubType")ClubType clubType);
    int insertByClubType(ClubType clubType);
}
