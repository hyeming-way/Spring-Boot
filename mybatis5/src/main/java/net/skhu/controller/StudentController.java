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
		model.addAttribute("departments", departmentMapper.findAll());

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

			model.addAttribute("departments", departmentMapper.findAll());

			return "student/edit";
		}
	}


	//학생 등록 화면 요청
	@GetMapping("create")
	public String create(Model model) {

		StudentEdit studentEdit = new StudentEdit();
		model.addAttribute("studentEdit", studentEdit);
		model.addAttribute("departments", departmentMapper.findAll());

		return "student/edit";

	}


	//학생 등록 요청
	//POST http://localhost:8088/student/create
	@PostMapping("create")
	public String create(Model model, @Valid StudentEdit studentEdit,
			             BindingResult bindingResult) {

		try {

			log.debug("edit.html에서 등록할 학생 정보가 저장된 StudentEdit객체의 변수 정보들");
			log.debug(": " + studentEdit.toString());

			//유효성 검사 통과하지 않으면?
			if(bindingResult.hasErrors()) {
				throw new Exception("학생을 등록할 수 없습니다.");
			}

			//유효성 검사 통과하면? 학생등록을 하기위해
			//1. StudentEdit 객체의 변수정보들을 -> Student객체의 변수들에 전달해서 저장
			//방법 : ModelMapper객체 이용
			Student student = modelMapper.map(studentEdit, Student.class);

			//2. 학생등록(INSERT)작업하기위해 StudentMapper인터페이스를 구현한 자식 익명 객체의
			//   insert메소드를 호출할 때 매개변수로 DTO역할을 하는 바로 위 Student객체 전달하여 DB작업함
			studentMapper.insert(student);

			//학생등록에 성공하면 학생목록을 DB에서 조회해 오기 위한 요청 URL주소 리턴
			return "redirect:list";

		} catch (Exception e) {
			//유효성 검사 통과하지않으면? StudentEdit객체를 ""를 작성하여 bindingResult객체에 저장하고
			//e.getMessage()를 이용해 "학생을 등록할 수 없습니다." 예외 메세지를 저장한 후
			bindingResult.reject("", null, e.getMessage());
			model.addAttribute("departments", departmentMapper.findAll());

			return "student/edit"; //학생 등록 화면 뷰 경로를 리턴
		}
	}


	//학생 삭제 요청
	//POST http://localhost:8088/student/edit?id=6&cmd=delete
	@PostMapping(value="edit", params="cmd=delete")
	public String delete(Model model, @Valid StudentEdit studentEdit,
						 BindingResult bindingResult) {

		try {
			//학생 정보를 삭제하기위해 매퍼의 메소드 호출시 매개변수로 삭제할 학생 ID 전달
			studentMapper.deleteByID(studentEdit.getId());

			//삭제에 성공하면 삭제후 리스트목록조회 재요청
			return "redirect:list";

		} catch (Exception e) {
			//삭제에 실패하면 에러메세지 저장 후
			bindingResult.reject("", null, e.getMessage());

			//삭제 실패후 edit.html로 가서 <select> option에 학과 정보보여주기 위해
			//DepartmentMapper인터페이스의 findAll()메소드를 호출해 조회한 학과정보가 저장된 List<Department>배열 반환받아
			//Model에 바인딩
			model.addAttribute("departments",  departmentMapper.findAll() );

			//다시 edit.html로 가서 에러 메세지 보여줌
			return "student/edit";

		}
	}






}





