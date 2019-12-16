package com.ams.controller;

import com.ams.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.ams.entity.Activity;
import com.ams.entity.User;
import com.ams.mapper.UserMapper;
import com.ams.utils.Layui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserMapper userMapper;
	
	@RequestMapping("/selectAll")
	 public Layui select(@RequestParam(required = false) String str,
	    		@RequestParam(value = "page")Integer page,@RequestParam(value = "limit")Integer limit) {
	        int count = userMapper.countByExample(str);
	        return Layui.data(count, userMapper.selectByUser(str,(page-1)*limit, limit));
	    }
	@RequestMapping("/selectAdminAll")
	 public Layui selectAdmin(@RequestParam(required = false) String str,
	    		@RequestParam(value = "page")Integer page,@RequestParam(value = "limit")Integer limit) {
		
	        int count = userMapper.countByAdmin(str);
	        return Layui.data(count, userMapper.selectByAdmin(str,(page-1)*limit, limit));
		
	}
	@PostMapping(value = "/delete", consumes = "application/json")
    public Integer delete(@RequestBody User entity) {
		User usr = entity;
//		usr.setUid(entity.getUid());
		usr.setUser_state("已回收");
        return userMapper.updateByUid(usr);
    }
	@PostMapping(value = "/update", consumes = "application/json")
    public Integer update(@RequestBody User entity) {
        return userMapper.updateByUid(entity);
    }
	@RequestMapping(value = "/selectnow")
	public User selectNow( int uid) {
		return userMapper.selectUserByUid(uid);
	}
	@RequestMapping(value = "/userSum")
    public Integer userSum() {		
        return userMapper.countuserSum();
    }
	
	@RequestMapping(value = "/adminSum")
    public Integer adminSum() {		
        return userMapper.countadminSum();
    }
	
	@PostMapping(value = "/updateAdmin", consumes = "application/json")
	public Integer updateAdmin(@RequestBody User entity) {
		return userMapper.updateByUid(entity);
	}
	@PostMapping(value = "/add", consumes = "application/json")
	public Integer add(@RequestBody User entity) {
		entity.setUser_state("可用");
		entity.setPwd("123456");
		int count=userMapper.insertUser(entity);
		return count;
	}
	//修改密码
	@PostMapping(value = "/setPwd",consumes = "application/json")
	@ResponseBody
	public  Object setPwd(@RequestParam(value = "oldPwd",required = false) String oldPwd,@RequestParam(value = "newPwd",required = false) String newPwd,@RequestParam(value = "uid",required = false)Integer uid){
		Map<String,Object> map=new HashMap<>();
		System.out.println(oldPwd);
		System.out.println(newPwd);
		System.out.println(uid);
		if(oldPwd!=null){
			User user=userMapper.selectBypassword(oldPwd,uid);
			Integer id=user.getUid();
			String sqlpassword=user.getPwd();
			if(sqlpassword.equals(oldPwd)){
				user.setUid(id);
				user.setPwd(newPwd);
				return userMapper.updateByUid(user);
			}else{
				map.put("code",0);
				map.put("msg","原密码不正确");
			}
		}else{
			map.put("code",1);
			map.put("msg","修改失败");
		}
		return  map;


	}
	@RequestMapping(value = "/uploadimg", method = {RequestMethod.POST})
	public Map<String, Object> upload(MultipartFile file,HttpServletRequest request){
		String filename=file.getOriginalFilename();
		String uuid = UUID.randomUUID()+"";
		//这里填上传到本地的路径
		File dest = new File(new File("src/main/resources/static/img/user").getAbsolutePath()+ "/" + uuid+"-"+filename);
		if (!dest.getParentFile().exists()) {
			dest.getParentFile().mkdirs();
		}
		try {
			file.transferTo(dest);
			String path="img/user/"+uuid+"-"+filename;
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




}
