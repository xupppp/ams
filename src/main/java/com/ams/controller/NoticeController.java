package com.ams.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import com.ams.dto.NoticeDTO;
import com.ams.entity.ActSign;
import com.ams.entity.Activity;
import com.ams.entity.Club;
import com.ams.entity.News;
import com.ams.entity.Notice;
import com.ams.entity.User;
import com.ams.mapper.ClubMapper;
import com.ams.mapper.NoticeMapper;
import com.ams.utils.Layui;


@RestController
@RequestMapping("/notice")
public class NoticeController {
	@Autowired
	private NoticeMapper noticeMapper;
	@Autowired
	private ClubMapper clubMapper;
	
	@RequestMapping("/select")
    public Layui select(@RequestParam(required = false) String str,
    		@RequestParam(value = "page")Integer page,@RequestParam(value = "limit")Integer limit) {
        int count = noticeMapper.countByExample(str);
        List<Notice> result=noticeMapper.selectByExample(str,(page-1)*limit, limit);
        Collections.reverse(result);
        return Layui.data(count, result);
    }
	
	@PostMapping(value = "/delete", consumes = "application/json")
    public Integer delete(@RequestBody Notice entity) {
		Notice notice=new Notice();
		notice.setNid(entity.getNid());
		notice.setNoti_state("已删除");
        return noticeMapper.updateByExample(notice);
    }
	
	@PostMapping(value = "/update", consumes = "application/json")
	public Integer update(@RequestBody Notice entity) throws ParseException {
        return noticeMapper.updateByExample(entity);
    }
	
	@PostMapping(value = "/add", consumes = "application/json")
    public Integer add(@RequestBody Notice entity) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		Date d = new Date();
        entity.setNoti_time(sdf.format(d));
        return noticeMapper.insertByExample(entity);
    }
	
	@RequestMapping("/selectOne")
    public NoticeDTO selectOne(@RequestParam(value = "nid")Integer nid) {
		Notice notice =new Notice();
		notice.setNid(nid);
		List<Notice> l=noticeMapper.listNotice(notice);
		Notice entity=l.get(0);
		String cid=String.valueOf(entity.getCid());
		if(cid.equals("0")||cid.equals("null")) {
			return noticeMapper.selectOne(1,nid);  //社联发布
			}
        return noticeMapper.selectOne(2,nid);  //社团发布
    }
	
	@RequestMapping("/selectByClub")
    public List<Notice> selectByClub(@RequestParam(value = "uid")Integer uid,@RequestParam(value = "noti_state")String noti_state){
		Club club=clubMapper.selectManagerClub(uid);
		Notice notice =new Notice();
		notice.setCid(club.getCid());
		notice.setNoti_state(noti_state);
		List<Notice> result=noticeMapper.listNotice(notice);

        return result;
    }
	
}

