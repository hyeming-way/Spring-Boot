package net.skhu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import net.skhu.dto.Department;

//학과 정보(department테이블) DB작업

@Mapper
public interface DepartmentMapper {

	@Select("SELECT * FROM department ORDER BY id")
	List<Department>   findAll();

}
