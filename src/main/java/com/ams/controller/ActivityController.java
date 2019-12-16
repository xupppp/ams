package com.ams.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
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

import com.ams.dto.ActivityDTO;
import com.ams.entity.ActSign;
import com.ams.entity.Activity;
import com.ams.entity.ActivityType;
import com.ams.entity.Club;
import com.ams.entity.ClubMembers;
import com.ams.entity.Field;
import com.ams.entity.News;
import com.ams.entity.User;
import com.ams.mapper.ActSignMapper;
import com.ams.mapper.ActivityMapper;
import com.ams.mapper.ClubMapper;
import com.ams.mapper.ClubMembersMapper;
import com.ams.mapper.FieldMapper;
import com.ams.utils.Layui;


@RestController
@RequestMapping("/activity")
public class ActivityController {
	@Autowired
	private ActivityMapper activityMapper;
	@Autowired
	private ActSignMapper actSignMapper;
	@Autowired
	private ClubMapper clubMapper;
	@Autowired
	private ClubMembersMapper clubmembersMapper;
	@Autowired
	private FieldMapper fieldMapper;
	
	@RequestMapping("/select")
    public Layui select(@RequestParam(required = false) String str,@RequestParam(required = false) int type,@RequestParam(value="act_state") String act_state,@RequestParam(required = false) String act_type,
    		@RequestParam(value = "page")Integer page,@RequestParam(value = "limit")Integer limit) {
        int count = activityMapper.countByExample(str,type,act_state,act_type);
        return Layui.data(count, activityMapper.selectByExample(str,type,act_state,act_type,(page-1)*limit, limit));
    }
	
	@RequestMapping("/selectNoFinished")
    public Layui selectNoFinished(@RequestParam(value = "page")Integer page,@RequestParam(value = "limit")Integer limit,@RequestParam(value = "atid")Integer atid) throws ParseException {
		List<Activity> result=activityMapper.selectByExample2(null,0,"已发布","全部",atid);
        Iterator<Activity> it = result.iterator();
		while(it.hasNext()){
		    Activity x = it.next();
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			Date start=sdf.parse(x.getStart_time());
			Date now=new Date();
				if(now.after(start)) {
					it.remove();
				}
		}
		int count=result.size();
		int fromindex=(page-1)*limit;
		int endindex=page*limit;
		if(endindex>count)
			endindex=count;
		return Layui.data(count, result.subList(fromindex,endindex));
    }
	
	@RequestMapping("/selectInner")
    public Layui selectInner(@RequestParam(value = "page")Integer page,@RequestParam(value = "limit")Integer limit,@RequestParam(value = "atid")Integer atid) throws ParseException {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        String s=session.getAttribute("loginUserId").toString();
        int loginUid=Integer.parseInt(s);
        List<ClubMembers> list=clubmembersMapper.listByUid(loginUid);
        List<Activity> result = new ArrayList<Activity>();
        for(ClubMembers m :list) {
        	Activity act=new Activity();
        	act.setCid(m.getCid());
        	act.setAct_state("已发布");
        	act.setAct_type("社内");
        	act.setAtid(atid);
        	 result.addAll(activityMapper.listAct(act));
        }
        Iterator<Activity> it = result.iterator();
		while(it.hasNext()){
		    Activity x = it.next();
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			Date start=sdf.parse(x.getStart_time());
			Date now=new Date();
				if(now.after(start)) {
					it.remove();
				}
		}
		int count=result.size();
		int fromindex=(page-1)*limit;
		int endindex=page*limit;
		if(endindex>count)
			endindex=count;
		return Layui.data(count, result.subList(fromindex,endindex));
    }
	
	@RequestMapping("/selectSign")
    public Layui selectSign(@RequestParam(required = false) String str,@RequestParam(value="actid") int actid,
    		@RequestParam(value = "page")Integer page,@RequestParam(value = "limit")Integer limit) {
        int count = actSignMapper.countByExample(str,actid);
        return Layui.data(count, actSignMapper.selectByExample(str,actid,(page-1)*limit, limit));
    }
	
	@RequestMapping("/selectOne")
    public ActivityDTO selectOne(@RequestParam(value = "actid")Integer actid) {
		Activity act =new Activity();
		act.setActid(actid);
		List<Activity> l=activityMapper.listAct(act);
		Activity entity=l.get(0);
		String uid=String.valueOf(entity.getUid());
		if(uid.equals("0")||uid.equals("null")) {
			return activityMapper.selectOne(1,actid);
		}
        return activityMapper.selectOne(2,actid);
    }
	
	@RequestMapping("/selectByClub")
    public List<Activity> selectByClub(@RequestParam(value = "uid")Integer uid,@RequestParam(value = "act_state")String act_state,@RequestParam(value = "type",required = false)Integer type) throws ParseException{
		Club club=clubMapper.selectManagerClub(uid);
		Activity act =new Activity();
		act.setCid(club.getCid());
		act.setAct_state(act_state);
		List<Activity> result=activityMapper.listAct(act);
		//判断已发布的活动是否已过期
		if(act_state.equals("已发布")) {
			Iterator<Activity> it = result.iterator();
			while(it.hasNext()){
			    Activity x = it.next();
			    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
				Date start=sdf.parse(x.getStart_time());
				Date now=new Date();
				if(type==1) {
					if(now.after(start)) {
						it.remove();
					}
				}
				else {
					if(now.before(start)) {
						it.remove();
					}
				}
			}
		}
        return result;
    }
	
	@RequestMapping("/selectByUser")
    public List<Activity> selectByUser(@RequestParam(value = "uid")Integer uid,@RequestParam(value = "type")Integer type) throws ParseException{
		List<Activity> result=actSignMapper.selectByUser(uid);
		Iterator<Activity> it = result.iterator();
		while(it.hasNext()){
		    Activity x = it.next();
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			Date start=sdf.parse(x.getStart_time());
			Date now=new Date();
			if(type==1) {
				if(now.after(start)) {
					it.remove();
				}
			}
			else {
				if(now.before(start)) {
					it.remove();
				}
			}
		}
        return result;
    }
	
	@PostMapping(value = "/delete", consumes = "application/json")
    public Integer delete(@RequestBody Activity entity) {
		Activity act=new Activity();
		act.setActid(entity.getActid());
		act.setAct_state("已删除");
        return activityMapper.updateByExample(act);
    }
	
	@PostMapping(value = "/update", consumes = "application/json")
    public Integer update(@RequestBody Activity entity) throws ParseException {
		//判断场地是否占用
		if((entity.getStart_time()!=null && entity.getStart_time().equals("")==false) ||(entity.getEnd_time()!=null && entity.getEnd_time().equals("")==false)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			Date start=sdf.parse(entity.getStart_time());
			Date end=sdf.parse(entity.getEnd_time());
			Activity t=new Activity();
			t.setFid(entity.getFid());
			t.setAct_state("已发布");
			List<Activity> l=activityMapper.listAct(t);
			for(Activity i:l) {
				Date s2=sdf.parse(i.getStart_time());
				Date e2=sdf.parse(i.getEnd_time());
				if(!(start.after(e2)||end.before(s2))&&i.getActid()!=entity.getActid()) {
					return -1;
				}
			}
		}
		//判断名额是否超过场地人数限制
		if(entity.getAct_numberlimit()>0||entity.getFid()>0) {
		    Field f=fieldMapper.selectOne(entity.getFid());
		    int limit=f.getF_numberlimit();
		    if(limit<entity.getAct_numberlimit()) {
		    	return -2;
		    }
		}
        return activityMapper.updateByExample(entity);
    }
	
	@RequestMapping(value = "/listCharge")
    public User listCharge(int actid) {
        return activityMapper.selectCharge(actid);
    }
	
	
	@RequestMapping(value = "/countSign")
    public Integer countSign(@RequestParam(value = "actid")int actid) {
        return actSignMapper.countByAct(actid);
    }
	
	@PostMapping(value = "/cancelSign", consumes = "application/json")
    public Integer cancelSign(@RequestBody ActSign entity) {
		ActSign sign=new ActSign();
		sign.setAsid(entity.getAsid());
		sign.setSign_state("取消");
        return actSignMapper.updateByExample(sign);
    }
	
	@PostMapping(value = "/addSign", consumes = "application/json")
    public Integer addSign(@RequestBody ActSign entity) {
		if(entity.getUid()==-1) {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	        HttpSession session = request.getSession();
	        String s=session.getAttribute("loginUserId").toString();
	        int loginUid=Integer.parseInt(s);
	        entity.setUid(loginUid);
		}
		int result=-1;
		ActSign sign=actSignMapper.selectOne(entity);
		if(sign==null)
			result=actSignMapper.add(entity);
		else if(sign.getSign_state().equals("取消")) {
			ActSign t=new ActSign();
			t.setAsid(sign.getAsid());
			t.setSign_state("报名");
			result=actSignMapper.updateByExample(t);
		}
			
        return result;
    }
	
	@PostMapping(value = "/add", consumes = "application/json")
    public Integer add(@RequestBody Activity entity) throws ParseException {
		//判断场地是否可用
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		Date start=sdf.parse(entity.getStart_time());
		Date end=sdf.parse(entity.getEnd_time());
		Activity t=new Activity();
		t.setAct_state("已发布");
		t.setFid(entity.getFid());
		List<Activity> l=activityMapper.listAct(t);		
		for(Activity i:l) {
			Date s2=sdf.parse(i.getStart_time());
			Date e2=sdf.parse(i.getEnd_time());
			if(!(start.after(e2)||end.before(s2))) {
				return -1;
			}
		}
		//判断名额是否超过场地人数限制
		Field f=fieldMapper.selectOne(entity.getFid());
	    int limit=f.getF_numberlimit();
	    if(limit<entity.getAct_numberlimit()) {
	    	return -2;
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
		//设置当前时间
		Date d = new Date();
        entity.setAct_time(sdf.format(d));
        return activityMapper.insertByExample(entity);
    }

	@RequestMapping("/selectIndex")
    public List<Activity> selectIndex(@RequestParam(value = "num")Integer num){		
		List<Activity> list=activityMapper.selectHot();
		if(list.size()<num) {
			int time=num-list.size();
			for(int i=0;i<time;i++) {
				list.add(new Activity());
			}
		}
		List<Activity> result=list.subList(0, num);
        return result;
    }
	
	@RequestMapping(value = "/actSum")
    public Integer actSum() {  
        return activityMapper.countactSum();
    }
 
 @RequestMapping(value = "/newactSum")
    public Integer newactSum() {  
        return activityMapper.countnewactSum();
    }
 @RequestMapping(value = "/checkAct")
 public Integer checkAct() {  
     return activityMapper.countcheckAct();
 }
}
