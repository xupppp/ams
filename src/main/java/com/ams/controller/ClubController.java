package com.ams.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.ams.dto.ClubDTO;
import com.ams.entity.ActSign;
import com.ams.entity.Activity;
import com.ams.entity.Club;
import com.ams.entity.ClubMembers;
import com.ams.entity.ClubType;
import com.ams.entity.Field;
import com.ams.entity.News;
import com.ams.entity.User;
import com.ams.mapper.ActivityMapper;
import com.ams.mapper.ClubMapper;
import com.ams.mapper.ClubMembersMapper;
import com.ams.mapper.ClubTypeMapper;
import com.ams.mapper.FieldMapper;
import com.ams.mapper.NewsMapper;
import com.ams.utils.Layui;

@RestController
@RequestMapping("/club")
public class ClubController {
	
	@Autowired
	private ClubMapper clubMapper;
	
	@Autowired
	private ClubMembersMapper clubMembersMapper;
	
	@Autowired
	private ActivityMapper activityMapper;
	@Autowired
	private NewsMapper newsMapper;
	
	@RequestMapping(value = "/checkClub")
    public Integer checkClub() {		
        return clubMapper.countcheckClub();
    }
	
	@RequestMapping(value = "/clubSum")
    public Integer clubSum() {		
        return clubMapper.countclubSum();
    }
	
	@RequestMapping(value = "/newclubSum")
    public Integer newclubSum() {		
        return clubMapper.countnewclubSum();
    }
	
	@RequestMapping("/list")
    public List<Club> list() {
        return clubMapper.listAll();
    }
	
	@RequestMapping(value = "/listManagerClub")
    public Club listManagerClub(int uid) {		
        return clubMapper.selectManagerClub(uid);
    }
	
	@RequestMapping(value = "/listReason")
    public ClubMembers listReason(int uid,int cid) {		
        return clubMembersMapper.selectReason(uid,cid);
    }
	
	@RequestMapping("/select")
    public Layui select(@RequestParam(required = false) String str,
    		@RequestParam(value = "page")Integer page,@RequestParam(value = "limit")Integer limit) {
        int count = clubMapper.countByExample(str);
        return Layui.data(count, clubMapper.selectByExample(str,(page-1)*limit, limit));
    }
	
	@RequestMapping("/selectRun")
    public Layui selectRun(@RequestParam(required = false) String str,@RequestParam(value = "ctid")Integer ctid,
    		@RequestParam(value = "page")Integer page,@RequestParam(value = "limit")Integer limit) {
        int count = clubMapper.countRun(str,ctid);
        return Layui.data(count, clubMapper.selectRun(str,(page-1)*limit, limit,ctid));
    }
	
	@RequestMapping("/selectApply")
    public Layui selectApply(@RequestParam(required = false) String str,@RequestParam(value = "cid") int cid,
    		@RequestParam(value = "page")Integer page,@RequestParam(value = "limit")Integer limit) {
        int count = clubMembersMapper.countByCid(str,cid);
        return Layui.data(count, clubMembersMapper.selectByCid(str,cid,(page-1)*limit, limit));
    }
	
	@RequestMapping("/selectMemberTop")
    public List<Club> selectMemberTop() {
        List<Club> club=clubMembersMapper.selectMemberTop();
        List<Club> top6=club.subList(0, 6);
        return top6;
    }
	
	@RequestMapping("/selectActTop")
    public List<Club> selectActTop() {
        List<Club> club=activityMapper.selectActTop();
        if(club.size()<6) {
        	int num=6-club.size();
        	for(int i=0;i<num;i++) {
        		club.add(new Club());
        	}
        }
        List<Club> top6=club.subList(0, 6);
        return top6;
    }
	
	@RequestMapping("/selectTop")
    public List<Club> selectTop() {
        List<Club> club=clubMembersMapper.selectTop();
        List<Club> top3=club.subList(0, 3);
        return top3;
    }
	
	@RequestMapping("/selectGood")
    public List<Club> selectGood() {
        List<Club> club=activityMapper.selectTop();
        List<Club> top3=club.subList(0, 3);
        return top3;
    }
	
	@RequestMapping("/select2")
    public Layui select2(@RequestParam(required = false) String str,
    		@RequestParam(value = "page")Integer page,@RequestParam(value = "limit")Integer limit) {
        int count = clubMapper.countByExample(str);
        return Layui.data(count, clubMapper.selectByExample(str,(page-1)*limit, limit));
    }
	
	@RequestMapping("/selectnew")
    public Layui selectnew(@RequestParam(required = false) String str,
    		@RequestParam(value = "page")Integer page,@RequestParam(value = "limit")Integer limit) {
        int count = clubMapper.countByNew(str);
        return Layui.data(count, clubMapper.selectByNew(str,(page-1)*limit, limit));
    }
	
	@RequestMapping("/selectmodify")
    public Layui selectmodify(@RequestParam(required = false) String str,
    		@RequestParam(value = "page")Integer page,@RequestParam(value = "limit")Integer limit) {
        int count = clubMapper.countByModify(str);
        return Layui.data(count, clubMapper.selectByModify(str,(page-1)*limit, limit));
    }
	
	@RequestMapping("/selectMember")
    public Layui selectMember(@RequestParam(required = false) String str,@RequestParam(value = "cid") int cid,
    		@RequestParam(value = "page")Integer page,@RequestParam(value = "limit")Integer limit) {
        int count = clubMembersMapper.countByExample(str,cid);
        return Layui.data(count, clubMembersMapper.selectByExample(str,cid,(page-1)*limit, limit));
    }
	
	@RequestMapping("/selectMemberStu")
    public Layui selectMemberStu(@RequestParam(required = false) String str,@RequestParam(value = "cid") int cid,
    		@RequestParam(value = "page")Integer page,@RequestParam(value = "limit")Integer limit) {
        int count = clubMembersMapper.countByStu(str,cid);
        return Layui.data(count, clubMembersMapper.selectByStu(str,cid,(page-1)*limit, limit));
    }
	
	@RequestMapping("/selectOne")
    public ClubDTO selectOne(@RequestParam(value = "cid")Integer cid) {
		Club club =new Club();
		club.setCid(cid);
        return clubMapper.selectOne(cid);
    }
	
	@RequestMapping("/selectUid")
	public User selectUid(int cid) {
        return clubMapper.selectPresident(cid);
    }
	
	@PostMapping(value = "/add", consumes = "application/json")
    public Integer add(@RequestBody Club entity) {
        entity.setClub_state("运行");
        int count1=clubMapper.insertByExample(entity);
        int count2;
        ClubMembers m=new ClubMembers();
        m.setCid(entity.getCid());
        m.setUid(entity.getClub_uid());
        m.setPresident_flag(1);
        m.setMem_state("在社");
        count2=clubMembersMapper.insertByExample(m);
        return count2;
    }
	
	@PostMapping(value = "/stuadd", consumes = "application/json")
    public Integer stuadd(@RequestBody Club entity) {
		int result=-1;
        entity.setClub_state("成立待审");
        int count1=clubMapper.insertByExample(entity);
        ClubMembers m=new ClubMembers();
        m.setCid(entity.getCid());
        m.setUid(entity.getUid());
        m.setPresident_flag(1);
        m.setMem_state("在社");
        int count2=clubMembersMapper.insertByExample(m);
        if(count1==1&&count2==1) {
        	result=1;
        }
        else {
        	return result;
        }
        return result;
    }
	
	@PostMapping(value = "/stumodify", consumes = "application/json")
    public Integer stumodify(@RequestBody Club entity) {
        entity.setClub_state("修改待审");
        return clubMapper.updateByExample(entity);
    }
	
	@Transactional
	@PostMapping(value = "/delete", consumes = "application/json")
    public Integer delete(@RequestBody Club entity) {
		  int result=0,result1=0,result2=0,result3=0,result4=0;
		  Club club=new Club();
		  club.setCid(entity.getCid());
		  club.setClub_state("删除");
		  result1=clubMapper.updateByExample(club);
		  result2=clubMapper.updateByExampleMember(club);
		  //删除对应活动
		  Activity t1=new Activity();
		  t1.setCid(entity.getCid());
		  List<Activity> acts=activityMapper.listAct(t1);
		  for(Activity a :acts) {
			  Activity t2=new Activity();
			  t2.setActid(a.getActid());
			  t2.setAct_state("已删除");
			  result3=activityMapper.updateByExample(t2);
			  if(result3==0)
				  return 0;
		  }
		//删除对应新闻
		  News t3=new News();
		  t3.setCid(entity.getCid());
		  List<News> newss=newsMapper.listNews(t3);
		  for(News n :newss) {
			  News t4=new News();
			  t4.setNewsid(n.getNewsid());
			  t4.setNews_state("已删除");
			  result4=newsMapper.updateByExample(t4);
			  if(result4==0)
				  return 0;
		  }		  
		  if(result1>0&&result2>0)
			  result=1;
		  return result; 
    }
	
	@Transactional
	@PostMapping(value = "/dismiss")
    public Integer dismiss(@RequestParam(value = "cid") int cid) {
		  int result1=0,result2=0,result=0;
		  result1=clubMapper.updateByDismiss(cid);  //将原社长标记取消
		  result2=clubMapper.updateByDismiss(cid);  //标记新社长
		  if(result1==1&&result2==1)
			  result=1;
	      return result;
    }
	
	@PostMapping(value = "/passnew", consumes = "application/json")
    public Integer passnew(@RequestBody Club entity) {
		Club club=new Club();
		club.setCid(entity.getCid());
		club.setClub_state("运行");
        return clubMapper.updateByExample(club);
    }
	
	@PostMapping(value = "/refusenew", consumes = "application/json")
    public Integer refusenew(@RequestBody Club entity) {
		Club club=new Club();
		club.setCid(entity.getCid());
		club.setClub_state("审核失败");
        return clubMapper.updateByExample(club);
    }
	
	@PostMapping(value = "/delmember", consumes = "application/json")
    public Integer delmember(@RequestBody ClubMembers entity) {
		ClubMembers clubMembers=new ClubMembers();
		clubMembers.setCmid(entity.getCmid());
		clubMembers.setMem_state("已退社");
        return clubMembersMapper.updateByExample(clubMembers);
    }
	
	@PostMapping(value = "/addmember", consumes = "application/json")
    public Integer addmember(@RequestBody ClubMembers entity) {
        int result=-1;
        ClubMembers clubmembers=clubMembersMapper.selectOne(entity);
        if(clubmembers==null)
            result=clubMembersMapper.add(entity);
        else if(clubmembers.getMem_state().equals("已退社")) {
        	ClubMembers m=new ClubMembers();
            m.setCmid(clubmembers.getCmid());
            m.setMem_state("在社");
            result=clubMembersMapper.updateByExample(m);
        }
   
        return result;
    }
	
	@PostMapping(value = "/addmemberStu", consumes = "application/json")
    public Integer addmemberStu(@RequestBody ClubMembers entity) {
		if(entity.getUid()==-1) {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	        HttpSession session = request.getSession();
	        String s=session.getAttribute("loginUserId").toString();
	        int loginUid=Integer.parseInt(s);
	        entity.setUid(loginUid);
		}
		int result=-1;
		ClubMembers clubmembers=clubMembersMapper.selectOne(entity);
		if(clubmembers==null)
			result=clubMembersMapper.addstu(entity);
		else if(clubmembers.getMem_state().equals("已退社")) {
			ClubMembers m=new ClubMembers();
			m.setCmid(clubmembers.getCmid());
			m.setMem_state("待审核");
			result=clubMembersMapper.updateByExample(m);
		}
			
        return result;
    }
	
	@PostMapping(value = "/passStu", consumes = "application/json")
    public Integer passStu(@RequestBody ClubMembers entity) {
		int result=-1;
		ClubMembers clubmembers=clubMembersMapper.selectApplyOne(entity);
		if(clubmembers==null)
			result=clubMembersMapper.addApply(entity);
		else if(clubmembers.getMem_state().equals("审核失败")) {
			ClubMembers m=new ClubMembers();
			m.setCmid(clubmembers.getCmid());
			m.setMem_state("在社");
			result=clubMembersMapper.updateByExample(m);
		}
		return result;
    }
	
	@PostMapping(value = "/refuseStu", consumes = "application/json")
    public Integer refuseStu(@RequestBody ClubMembers entity) {
		ClubMembers m=clubMembersMapper.selectApplyOne(entity);
		m.setMem_state("审核失败");
        return clubMembersMapper.updateByExample(m);
    }
	
	@PostMapping(value = "/deleteStu", consumes = "application/json")
    public Integer deleteStu(@RequestBody ClubMembers entity) {
		int result=-1;
		ClubMembers m=clubMembersMapper.selectDeleteOne(entity);
		if(m.getPresident_flag()==1) {
			return result;
		}
		else {
			m.setMem_state("已退社");
	        result=clubMembersMapper.updateByExample(m);
		}
		return result;
    }
	
	@PostMapping(value = "/update", consumes = "application/json")
    public Integer update(@RequestBody Club entity) {
        return clubMapper.updateByExample(entity);
    }
	
	@RequestMapping(value = "/listPresident")
    public User listPresident(int cid) {
        return clubMapper.selectPresident(cid);
    }
	
	@RequestMapping(value = "/selectUser")
    public User selectUser(int uid) {
        return clubMapper.selectUser(uid);
    }
	
	
	@RequestMapping("/listmember")
    public List<User> listmember(int cid) {
        return clubMapper.listUser(cid);
    }
	
	@RequestMapping(value = "/more")
    public Club more(int cid) {
        return clubMapper.selectMore(cid);
    }
	
	@RequestMapping(value = "/countMember")
    public Integer countMember(@RequestParam(value = "cid")int cid) {
        return clubMembersMapper.countByMember(cid);
    }
	
	@RequestMapping(value = "/modifyManagerClub")
    public Club modifyManagerClub(int cid) {		
        return clubMapper.modifyManagerClub(cid);
    }
	
	@Transactional
	@RequestMapping(value = "/turnPresident")
    public Integer turnPresident(@RequestParam(value = "uid") int uid,@RequestParam(value = "cid") int cid) {
		int result1=0,result2=0,result=0;
		result1=clubMapper.updatePresident(cid);  //将原社长标记取消
		result2=clubMapper.updatePresident2(cid,uid);  //标记新社长
		if(result1==1&&result2==1)
			result=1;
        return result;
    }
	
	@RequestMapping("/selectMine")
	public List<Club> selectMine(@RequestParam(value = "uid") int uid) {
        return clubMapper.selectByUid(uid);
    }
	
	@PostMapping(value = "/leaveStu", consumes = "application/json")
    public Integer leaveStu(@RequestBody ClubMembers entity) {
		int result=-1;
		ClubMembers clubmembers=clubMembersMapper.selectExistOne(entity);
		if(clubmembers.getPresident_flag()==1) {
			return result;
		}
		else {
			ClubMembers m=new ClubMembers();
			m.setCmid(clubmembers.getCmid());
			m.setMem_state("已退社");
			result=clubMembersMapper.updateByExample(m);
		}
		return result;
    }
	
	@RequestMapping(value = "/uploadimg", method = {RequestMethod.POST})
	public Map<String, Object> upload(MultipartFile file,HttpServletRequest request){	
		String filename=file.getOriginalFilename();
		String uuid = UUID.randomUUID()+"";
		//这里填上传到本地的路径
		File dest = new File(new File("src/main/resources/static/img/club").getAbsolutePath()+ "/" + uuid+"-"+filename);
		if (!dest.getParentFile().exists()) { 
            dest.getParentFile().mkdirs();
        }
		try {
			file.transferTo(dest);
			String path="img/club/"+uuid+"-"+filename;
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
	
	@RequestMapping("/selectdel")
    public Layui selectdel(@RequestParam(required = false) String str,
    		@RequestParam(value = "page")Integer page,@RequestParam(value = "limit")Integer limit) {
        int count = clubMapper.countByDel(str);
        return Layui.data(count, clubMapper.selectByDel(str,(page-1)*limit, limit));
}

@PostMapping(value = "/back", consumes = "application/json")
    public Integer back(@RequestBody Club entity) {
		int result=-1;
		Club club=new Club();
		club.setCid(entity.getCid());
		club.setClub_state("运行");
        result=clubMapper.updateByExample(club);
        result=clubMapper.updateByExampleMemberBack(club);
        return result;
}

}

	


