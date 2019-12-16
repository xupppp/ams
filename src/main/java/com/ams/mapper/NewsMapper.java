package com.ams.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ams.dto.NewsDTO;
import com.ams.entity.News;
import com.ams.entity.User;

public interface NewsMapper {
	int countByExample(@Param("str") String str,@Param("type") int type,@Param("news_type") String news_type,@Param("news_state") String news_state);
	List<News> selectByExample(@Param("str") String str,@Param("type") int type,@Param("news_type") String news_type,@Param("news_state") String news_state,@Param("offset") int offset, @Param("limit") int limit);
	int updateByExample(News news);
	int insertByExample(News news);
	List<News> listNews(News news);
	User selectCharge(@Param("newsid") int newsid);
	NewsDTO selectOne(@Param("type")int type,@Param("newsid")int newsid);
	
	int countnewsSum();
	int countnewnewsSum();
	int countcheckNews();
}
