package com.ams.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.ams.dto.NewsDTO;
import com.ams.entity.Activity;
import com.ams.entity.Club;
import com.ams.entity.News;
import com.ams.entity.User;
import com.ams.mapper.ClubMapper;
import com.ams.mapper.NewsMapper;
import com.ams.utils.Layui;

import freemarker.core.ParseException;


@RestController
@RequestMapping("/news")
public class NewsController {
	@Autowired
	private NewsMapper newsMapper;
	@Autowired
	private ClubMapper clubMapper;
	
	@RequestMapping("/select")
    public Layui select(@RequestParam(required = false) String str,@RequestParam(value = "type")Integer type,@RequestParam(required = false)String news_type,@RequestParam(value="news_state")String news_state,
    		@RequestParam(value = "page")Integer page,@RequestParam(value = "limit")Integer limit) {
		if(news_type!=null&&news_type.equals("0"))
			news_type=null;
        int count = newsMapper.countByExample(str,type,news_type,news_state);
        List<News> result=newsMapper.selectByExample(str,type,news_type,news_state,(page-1)*limit, limit);
        Collections.reverse(result);
        return Layui.data(count, result);
    }
	
	@PostMapping(value = "/delete", consumes = "application/json")
    public Integer delete(@RequestBody News entity) {
		News news=new News();
		news.setNewsid(entity.getNewsid());
		news.setNews_state("已删除");
        return newsMapper.updateByExample(news);
    }
	
	@PostMapping(value = "/update", consumes = "application/json")
    public Integer update(@RequestBody News entity) {
        return newsMapper.updateByExample(entity);
    }
	
	@PostMapping(value = "/add", consumes = "application/json")
    public Integer add(@RequestBody News entity) throws ParseException {
		if(entity.getNews_img().equals("")||entity.getNews_img()==null) {
			if(entity.getNews_type().equals("社团风采")) {
				entity.setNews_img("img/news/shetuanfengcai.png;");
			}
			else if(entity.getNews_type().equals("活动回顾")) {
				entity.setNews_img("img/news/huodonghuigu.png;");
			}
			else if(entity.getNews_type().equals("公示")) {
				entity.setNews_img("img/news/gongshi.png;");
			}
		}
		//获取当前用户
		if(entity.getUid()==-1) {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	        HttpSession session = request.getSession();
	        String s=session.getAttribute("loginUserId").toString();
	        int loginUid=Integer.parseInt(s);
	        entity.setUid(loginUid);
		}
		//获取当前社团
		if(entity.getUid()==-2) {
			entity.setUid(0);
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	        HttpSession session = request.getSession();
	        String s=session.getAttribute("loginUserId").toString();
	        int loginUid=Integer.parseInt(s);
			Club club=clubMapper.selectManagerClub(loginUid);
			entity.setCid(club.getCid());
		}
		//设置发布时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		Date d = new Date();
        entity.setNews_time(sdf.format(d));
        return newsMapper.insertByExample(entity);
    }
	
	@RequestMapping("/selectOne")
    public NewsDTO selectOne(@RequestParam(value = "newsid")Integer newsid) {
  News news =new News();
  news.setNewsid(newsid);
  List<News> l=newsMapper.listNews(news);
  News entity=l.get(0);
  String cid=String.valueOf(entity.getCid());
  if(cid.equals("0")||cid.equals("null")) {
   return newsMapper.selectOne(1,newsid);  //社联发布
  }
        return newsMapper.selectOne(2,newsid);  //社团发布
    }
	
	@RequestMapping("/selectByClub")
    public List<News> selectByClub(@RequestParam(value = "uid")Integer uid,@RequestParam(value = "news_state")String news_state){
		Club club=clubMapper.selectManagerClub(uid);
		News news=new News();
		news.setCid(club.getCid());
		news.setNews_state(news_state);
		List<News> result=newsMapper.listNews(news);

        return result;
    }
	
	@RequestMapping(value = "/uploadimg", method = {RequestMethod.POST})
	public Map<String, Object> upload(MultipartFile file,HttpServletRequest request){	
		String filename=file.getOriginalFilename();
		String uuid = UUID.randomUUID()+"";
		//这里填上传到本地的路径
		File dest = new File(new File("src/main/resources/static/img/news").getAbsolutePath()+ "/" + uuid+"-"+filename);
		if (!dest.getParentFile().exists()) { 
            dest.getParentFile().mkdirs();
        }
		try {
			file.transferTo(dest);
			String path="img/news/"+uuid+"-"+filename;
			Map<String,Object> map2=new HashMap<>();
	        Map<String,Object> map=new HashMap<>();
	        map.put("code",0);
	        map.put("msg","");
	        map.put("data",map2);
	        map2.put("src",path);
	        return map;
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Map<String,Object> map=new HashMap<>();
        map.put("code",1);
        map.put("msg","");
        return map;
	}
	
	@RequestMapping("/selectIndex")
    public List<News> selectIndex(@RequestParam(value = "num")Integer num){
		News news=new News();
		news.setNews_state("已发布");
		List<News> list=newsMapper.listNews(news);
		Collections.reverse(list);
		List<News> result=list.subList(0, num);
        return result;
    }
	
	@RequestMapping(value = "/newsSum")
    public Integer newsSum() {  
        return newsMapper.countnewsSum();
    }
 
    @RequestMapping(value = "/newnewsSum")
    public Integer newnewsSum() {  
        return newsMapper.countnewnewsSum();
    }
    @RequestMapping(value = "/checkNews")
    public Integer checkNews() {  
        return newsMapper.countcheckNews();
    }

}
