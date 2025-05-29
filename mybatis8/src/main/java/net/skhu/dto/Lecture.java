package net.skhu.dto;

import lombok.Data;

@Data
public class Lecture {

	//lecture테이블
	int id;
	String title;
	int professorId;
	int year;
	String semester;
	String room;

}
