package com.ams.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ams.entity.Club;
import com.ams.entity.User;
import com.ams.mapper.ClubMapper;
import com.ams.mapper.UserMapper;



@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private ClubMapper clubMapper;
	
	
	@PostMapping("/admin")
    public Integer loginAdmin(@RequestParam(value = "emid") String emid, @RequestParam(value = "pwd") String pwd) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        Integer result = 0;
        
        User example=new User();
        example.setEmid(emid);
        example.setPwd(pwd);
        example.setType("管理员");
        User user=userMapper.selectUserByLogin(example);
        if(user!=null) {
        	if(user.getType().equals("超级管理员")) {
        		result = 1;
                session.setAttribute("loginUserType", 1);
                session.setAttribute("loginUserId", user.getUid());
        	}
        	else if(user.getType().equals("管理员")) {
        		result = 2;
                session.setAttribute("loginUserType", 2);
                session.setAttribute("loginUserId", user.getUid());
        	}
        }
        return result;        
    }
	
//	@ApiOperation(value="学生登录", notes="根据用户名和密码验证用户，返回0为用户名或密码错误，返回1为验证正确")
	@PostMapping("/student")
    public Integer loginStudent(@RequestParam(value = "sduid") String sduid, @RequestParam(value = "pwd") String pwd) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        Integer result = 0;
        
        User example=new User();
        example.setSduid(sduid);      
        example.setPwd(pwd);
        example.setType("学生");
        User user=userMapper.selectUserByLogin(example);
        if(user!=null) {      	
        		result = 1;
                session.setAttribute("loginUserType", 3);
                session.setAttribute("loginUserId", user.getUid());
      
        }
        return result;        
    }
	
	@RequestMapping("/logout")
    public int logout() {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            HttpSession session = request.getSession();
            session.removeAttribute("loginUserType");
            session.removeAttribute("loginUserId");
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
	
	@GetMapping("/getLogin")
    public int getCurrentLoginUser() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        String loginUser = session.getAttribute("loginUserId").toString();
        int loginUid=Integer.parseInt(loginUser);
        return loginUid;
    }
	@GetMapping("/getLoginUser")
    public User getCurrentLoginUser2() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        String loginUser = session.getAttribute("loginUserId").toString();
        int loginUid=Integer.parseInt(loginUser);
        User u=userMapper.selectUserByUid(loginUid);
        return u;
    }
	
	@GetMapping("/president")
    public int getPresident() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        String s=session.getAttribute("loginUserId").toString();
        int loginUid=Integer.parseInt(s);
        Club club=clubMapper.selectManagerClub(loginUid);
        if(club!=null) {
        	return 1;
        }
        return 0;
    }
	
}
