package net.skhu.dto;

import lombok.Data;

//한 학생의 수강신청한 정보를 저장할 DTO

@Data
public class Sugang {

	//sugang 테이블
	int id;				//수강ID
	int lectureId;		//강좌ID
	int studentId;		//학생ID
	boolean repeated;	//재수강 여부, MySQL TINYINT
	boolean cancel;		//수강취소 여부, MySQL TINYINT
	String grade;		//학점

	//student테이블
	String studentNo;	//학번
	String name;		//학생이름

	//lecture테이블
	String title;		//수강과목
	int year;			//강좌개설년도
	String semester;	//학기정보

}
