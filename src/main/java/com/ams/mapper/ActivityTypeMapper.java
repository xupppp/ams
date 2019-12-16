package com.ams.mapper;

import java.util.List;

import com.ams.entity.*;
import org.apache.ibatis.annotations.Param;

import com.ams.dto.ActivityDTO;


public interface ActivityTypeMapper {
    List<ActivityType> list();
    int countByActType(@Param("str") String str);
    List<ActivityType> selectByActType(@Param("str") String str, @Param("offset") int offset, @Param("limit") int limit);
    int updateByAtid(@Param("activityType")ActivityType activityType);
    int insertByActType(ActivityType activityType);
}
