package net.skhu.dto;

import lombok.Data;

//DTO : 학생 정보 조회하여 변수에 저장할 용도

@Data
public class Student {

	//student테이블
	int id;
	String studentNo;
	String name;
	int departmentId;
	String phone;
	String sex;
	String email;

}
