package com.ams.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ams.entity.Club;
import com.ams.entity.User;
import com.ams.mapper.ActivityMapper;
import com.ams.mapper.UserMapper;
import com.ams.utils.Layui;

@RestController
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private UserMapper userMapper;
	
	@RequestMapping("/list")
    public List<User> list() {
		User user=new User();
		user.setType("学生");
        return userMapper.selectByEntity(user);
    }
	
	@RequestMapping("/listNotPre")
    public List<User> listNotPre() {
		User user=new User();
		user.setType("学生");
        return userMapper.selectByNotPre(user);
    }
	
	@RequestMapping("/listNotIn")
    public List<User> listNotIn() {
		User user=new User();
		user.setType("学生");
        return userMapper.selectByNotIn(user);
    }

}
