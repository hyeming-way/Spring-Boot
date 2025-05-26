package net.skhu.dto;


//DTO : 학생 한사람의 정보 저장 후 제공할 용도
public class Student {

	String studentNO;
	String name;

	public String getStudentNO() {
		return studentNO;
	}
	public void setStudentNO(String studentNO) {
		this.studentNO = studentNO;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
