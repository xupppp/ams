package com.ams.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ams.entity.Field;
import com.ams.mapper.ActivityMapper;
import com.ams.mapper.FieldMapper;
import com.ams.utils.Layui;

@RestController
@RequestMapping("/field")
public class FieldController {
	
	@Autowired
	private FieldMapper fieldMapper;
	
	@RequestMapping("/list")
    public List<Field> list() {
        return fieldMapper.listAll();
    }

}
