package com.ams.controller;

import java.util.List;

import com.ams.entity.Club;
import com.ams.entity.ClubMembers;
import com.ams.entity.User;
import com.ams.mapper.ClubMapper;
import com.ams.mapper.ClubTypeMapper;
import com.ams.utils.Layui;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.ams.entity.ClubType;
import com.ams.mapper.ActivityMapper;


@RestController
@RequestMapping("/clubType")
public class ClubTypeController {

    @Autowired
    private ClubTypeMapper clubTypeMapper;
    @Autowired
    private ClubMapper clubMapper;
    
    @RequestMapping(value = "/list")
    public List<ClubType> listActType() {
        return clubTypeMapper.list();
    }
    @RequestMapping("/selectAll")
    public Layui select(@RequestParam(required = false) String str,
                        @RequestParam(value = "page")Integer page,@RequestParam(value = "limit")Integer limit) {
        int count = clubTypeMapper.countByClubType(str);
        return Layui.data(count, clubTypeMapper.selectByClubType(str,(page-1)*limit, limit));
    }
    @PostMapping(value = "/delete", consumes = "application/json")
    public Integer delete(@RequestBody ClubType entity) {
        ClubType clubType = entity;
        clubType.setClub_type_state("已回收");
        return clubTypeMapper.updateByCtid(clubType);
    }
    @PostMapping(value = "/update", consumes = "application/json")
    public Integer update(@RequestBody ClubType entity) {
        return clubTypeMapper.updateByCtid(entity);
    }
    @PostMapping(value = "/add", consumes = "application/json")
    public Integer add(@RequestBody ClubType entity) {
        entity.setClub_type_state("可用");
        int count=clubTypeMapper.insertByClubType(entity);
        return count;
    }
    @RequestMapping("/selectClub")
    public Layui selectAct(@RequestParam(required = false) String str,@RequestParam(value = "ctid",required = false) Integer ctid,
                           @RequestParam(value = "page")Integer page,@RequestParam(value = "limit")Integer limit) {
        int count =clubMapper.countByClub(str,ctid);
        return Layui.data(count, clubMapper.selectByClub(str,ctid,(page-1)*limit, limit));
    }

}
