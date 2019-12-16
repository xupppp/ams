package com.ams.controller;

import java.util.List;

import com.ams.entity.Activity;
import com.ams.entity.ClubType;
import com.ams.utils.Layui;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ams.entity.ActivityType;
import com.ams.mapper.ActivityMapper;
import com.ams.mapper.ActivityTypeMapper;

@RestController
@RequestMapping("/activityType")
public class ActivityTypeController {

    @Autowired
    private ActivityTypeMapper activityTypeMapper;

    @Autowired
    private ActivityMapper activityMapper;

    @RequestMapping(value = "/list")
    public List<ActivityType> listActType() {
        return activityTypeMapper.list();
    }

    @RequestMapping("/selectAllType")
    public Layui select(@RequestParam(required = false) String str,
                        @RequestParam(value = "page")Integer page, @RequestParam(value = "limit")Integer limit) {
        int count = activityTypeMapper.countByActType(str);
        return Layui.data(count, activityTypeMapper.selectByActType(str,(page-1)*limit, limit));
    }
    @PostMapping(value = "/delete", consumes = "application/json")
    public Integer delete(@RequestBody ActivityType entity) {
        ActivityType activityType = entity;
//		usr.setUid(entity.getUid());
        activityType.setAct_type_state("已回收");
        return activityTypeMapper.updateByAtid(activityType);
    }
    @PostMapping(value = "/update", consumes = "application/json")
    public Integer update(@RequestBody ActivityType entity) {
        return activityTypeMapper.updateByAtid(entity);
    }

    @PostMapping(value = "/add", consumes = "application/json")
    public Integer add(@RequestBody ActivityType entity) {
        entity.setAct_type_state("可用");
        int count=activityTypeMapper.insertByActType(entity);
        return count;
    }
    @RequestMapping("/selectAct")
    public Layui selectAct(@RequestParam(required = false) String str,@RequestParam(value = "atid",required = false) Integer atid,
                           @RequestParam(value = "page")Integer page,@RequestParam(value = "limit")Integer limit) {
        int count =activityMapper.countByAct(str,atid);
        return Layui.data(count, activityMapper.selectByAct(str,atid,(page-1)*limit, limit));
    }
}
