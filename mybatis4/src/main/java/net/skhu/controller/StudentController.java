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


	//주석추가
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
	@GetMapping("edit")
	public String edit(Model model, @RequestParam("id") int id) {

		//DTO
		Student student = studentMapper.findById(id);

		//모델
		StudentEdit studentEdit = modelMapper.map(student, StudentEdit.class);
		//주석추가

		//모델 역할을 하는 StudentEdit객체 저장
		model.addAttribute("studentEdit", studentEdit);
//		model.addAttribute("departments", departmentMapper.findAll());

		return "student/edit";

	}


	//수정 요청 : POST http://localhost:8088/student/edit?id=1
	@PostMapping("edit")
	public String edit(Model model, @Valid StudentEdit studentEdit,
					   BindingResult bindingResult) {

		try {
			log.debug( studentEdit.toString() );

			if(bindingResult.hasErrors()) {
				throw new Exception("수정할 수 없습니다.");
			}

			//StudentEdit 객체의 변수값들을 모두 Student객체에 저장시키고
			Student student = modelMapper.map(studentEdit, Student.class);

			//Student객체의 변수의 정보들을 DB에 저장(수정할 정보 UPDATE)
			studentMapper.update(student);

			return "redirect:list";

		} catch (Exception e) {

			bindingResult.reject("", null, e.getMessage());
			return "student/edit";
		}
	}


	//학생 등록 화면 요청
	@GetMapping("create")
	public String create(Model model) {

		StudentEdit studentEdit = new StudentEdit();
		model.addAttribute("studentEdit", studentEdit);

		return "student/edit";

	}


	//학생 등록 요청
	//POST http://localhost:8088/student/create
	@PostMapping("create")
	public String create(Model model, @Valid StudentEdit studentEdit,
			             BindingResult bindingResult) {





		return "";
	}




}





