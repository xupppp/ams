package com.ams.mapper;

import java.util.List;

import com.ams.entity.Field;

public interface FieldMapper {
	List<Field> listAll();
    Field selectOne(int fid);
}
