package net.skhu.dto;

import lombok.Data;

//DTO클래스는 주로 DB에서 조회한 데이터를 채우는 용도로 사용한다.

@Data
public class Student {

	int id;
	String studentNo;
	String name;
	int departmentId;
	String phone;
	String sex;
	String email;
	String departmentName;//소속 학과명
}
