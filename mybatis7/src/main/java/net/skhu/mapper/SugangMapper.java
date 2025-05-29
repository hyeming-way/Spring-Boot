package net.skhu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import net.skhu.dto.Sugang;

//@Mapper 3.5버전 이상에서만 사용 가능! 레거시에서는 사용 불가
@Mapper
public interface SugangMapper {

	//한 학생이 수강신청한 정보를 sugang테이블의 모든 열 값과,
	//student테이블의 studentNo열, name열 값들
	//그리고 lecture테이블의 title열, year열, semester열값들 JOIN해서 조회

	@Select("""
			SELECT g.*, s.studentNo, s.name, l.title, l.year, l.semester
			FROM sugang g
			JOIN student s ON g.studentId = s.id
			JOIN lecture l ON g.lectureId = l.id
			ORDER BY s.studentNo
			""")
	List<Sugang> findAll();



}
