package net.skhu.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import net.skhu.dto.Student;
import net.skhu.mapper.DepartmentMapper;
import net.skhu.mapper.StudentMapper;
import net.skhu.model.StudentEdit;

@Controller
@RequestMapping("student")
@Slf4j
public class StudentController {

	//StudentMapper인터페이스를 구현한 자식 구현객체 자동 의존주입
	@Autowired
	StudentMapper studentMapper;

	//DepartmentMapper인터페이스를 구현한 자식 구현객체 자동 의존주입
	@Autowired
	DepartmentMapper departmentMapper;


	//`ModelMapper`는 이 두 객체(Student,StudentEdit) 간의
	// **데이터를 자동으로 복사**해주는 도구입니다.
	//예를 들어, `,StudentEdit` 객체에 있는 변수값을
	//         `Student` 객체에 그대로 옮겨서 자동으로 저장할때,
	//         `ModelMapper`가 이를 자동으로 해줍니다.
	ModelMapper modelMapper = new ModelMapper();


	//student테이블의 모든 학생 레코드 조회해서 보여주자
	//요청주소 : http://localhost:8088/student/list
	//요청주소 : http://localhost:8088/student/list?name='최'
	@GetMapping("list")
	public String list(Model model, @RequestParam(value="name", defaultValue = "") String name ) {

		List<Student> students = studentMapper.findByName(name + "%");

		model.addAttribute("students", students);
		model.addAttribute("name", name);

		return "student/list";
	}


	//이 액션 메소드를 호출하기 위한 URL은
	//http://localhost:8088/student/edit?id=1
    //이 URL이 GET 요청될 때 이 액션 메소드가 호출된다.
	@GetMapping("edit")
	public String edit(Model model, @RequestParam("id") int id) {

		//DTO
		Student student = studentMapper.findById(id);

		//모델
		StudentEdit studentEdit = modelMapper.map(student, StudentEdit.class);
		/*
		위 코드는 다음과 같이 실행된다.
			StudentEdit 클래스 객체 한 개를 생성한다.
			student 객체의 속성값을, 방금 생성된 StudentEdit 객체에 채워 넣는다.
			서로 이름이 같은 속성값만 채워진다.
		    따라서 위 코드는 아래 코드와 같다.
			Student student = studentMapper.findById(id);

			StudentEdit studentEdit = new StudentEdit();
			studentEdit.setId(student.getId());
			studentEdit.setStudentNo(student.getStudentNo());
			studentEdit.setName(student.getName());
			studentEdit.setPhone(student.getPhone());
			studentEdit.setEmail(student.getEmail());
			studentEdit.setSex(student.getSex());
		*/


		//모델 역할을 하는 StudentEdit객체 저장
		model.addAttribute("studentEdit", studentEdit);
//		model.addAttribute("departments", departmentMapper.findAll());

		return "student/edit";

	}


	//	수정 요청 :  POST   http://localhost:8088/student/edit?id=11
	@PostMapping("edit")
	public String edit(Model model, @Valid StudentEdit student,
					   BindingResult bindingResult) {

		try {
			log.debug( student.toString() );

			if(bindingResult.hasErrors()) {
				throw new Exception("수정할 수 없습니다.");
			}

			//----- StudentEdit 객체의 변수값들을 모두 Student객체에 저장시키고
			//	    Student객체의 변수의 정보들을 DB에 저장하는 구현 생략

			return "redirect:list";

		} catch (Exception e) {

			bindingResult.reject("", null, e.getMessage());
			return "student/edit";

		}

	}









}





