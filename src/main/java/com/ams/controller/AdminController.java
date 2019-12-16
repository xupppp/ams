package com.ams.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ams.entity.User;
import com.ams.mapper.UserMapper;



@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
    private UserMapper userMapper;
	
	@PostMapping("/add")
    public Integer add(@RequestParam(value = "emid")String emid,@RequestParam(value = "pwd")String pwd,@RequestParam(value = "type")String type,@RequestParam(value = "name")String name,@RequestParam(value = "sex")String sex,@RequestParam(value = "major",required = false)String major,@RequestParam(value = "class_num",required = false)String class_num,@RequestParam(value = "phone",required = false)String phone,@RequestParam(value = "email",required = false)String email) {
		User r=userMapper.selectUserByEmid(emid);
		if(r!=null) {
			return -1;
		}
		User user=new User();
		user.setEmid(emid);
		user.setPwd(pwd);
		user.setName(name);
		user.setType(type);
		user.setEmail(email);
		user.setPhone(phone);
		user.setSex(sex);
		user.setMajor(major);
		user.setClass_num(class_num);
		user.setUser_state("可用");
        return userMapper.insertUser(user);
    }
	
	@PostMapping(value = "/delete")
    public Integer delete(@RequestParam(value = "uid")int uid) {
		User r=userMapper.selectUserByUid(uid);
		if(r.getEmid().equals("J000000"))
			return -1;
		User user=new User();
		user.setUid(uid);
		user.setUser_state("回收");
        return userMapper.updateByUid(user);       
    }
	 

	 @PostMapping("/updateInfo")
	 public Integer updateInfo(@RequestParam(value = "uid")int uid,@RequestParam(value = "emid",required = false)String emid,@RequestParam(value = "pwd",required = false)String pwd,@RequestParam(value = "type",required = false)String type,@RequestParam(value = "name",required = false)String name,@RequestParam(value = "sex",required = false)String sex,@RequestParam(value = "major",required = false)String major,@RequestParam(value = "class_num",required = false)String class_num,@RequestParam(value = "phone",required = false)String phone,@RequestParam(value = "email",required = false)String email,@RequestParam(value = "user_state",required = false)String user_state,@RequestParam(value = "user_logo",required = false)String user_logo) {
		User r=userMapper.selectUserByEmid(emid);
		if(r!=null)
			return -1;
		User user=new User();
		user.setUid(uid);
		user.setEmid(emid);
		user.setPwd(pwd);
		user.setName(name);
		user.setType(type);
		user.setEmail(email);
		user.setPhone(phone);
		user.setSex(sex);
		user.setMajor(major);
		user.setClass_num(class_num);
		user.setUser_state(user_state);
		user.setUser_logo(user_logo);
	    return userMapper.updateByUid(user);
	 }
	 
	 
}
