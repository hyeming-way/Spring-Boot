package com.example.demo.controller;

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

import com.example.demo.dto.Student;
import com.example.demo.mapper.DepartmentMapper;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.model.Pagination;
import com.example.demo.model.StudentEdit;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("student")
@Slf4j
public class StudentController {

    @Autowired StudentMapper studentMapper;
    @Autowired DepartmentMapper departmentMapper;
    ModelMapper modelMapper = new ModelMapper();

    
/*
http://localhost:8088/student/list
학생 목록 화면에 페이지 번호 목록을 출력하려면, 마지막 페이지가 몇 번인지 계산할 수 있어야 한다.
  이 계산을 할 때, 전체 학생 레코드 수가 필요하다.
  그래서 이 전체 학생 레코드 수를 조회해서 Pagination 객체에 채워서 뷰에 전달한다.
*/
    @GetMapping("list")
    public String list(Model model, Pagination pagination) {
    	
    	System.out.println("list() 호출됨");
    	
        List<Student> students = studentMapper.findAll(pagination);

        pagination.setRecordCount(studentMapper.getCount());//164 
        model.addAttribute("students", students);
        
     // layout.html로 렌더링하고, 'view'에 사용할 프래그먼트 경로 넘기기
        model.addAttribute("view", "student/list");
        
        
        return "layout"; // layout.html로 렌더링
    }
    
    /*
    1	201532006	최하은	1	010-4361-1145	여	choi064@skhu.ac.kr	소프트웨어공학과   Student
    2	201532007	김진영	3	010-3415-1238	남	kim073@skhu.ac.kr	정보통신공학과     Student
    3	201532008	나철진	3	010-8703-1239	남	na088@skhu.ac.kr	정보통신공학과    Student
    4	201532009	이익수	4	010-7875-1251	남	lee097@skhu.ac.kr	글로컬IT공학과   Student
    5	201532010	이제문	1	010-7700-1333	남	lee107@skhu.ac.kr	소프트웨어공학과  Student
    6	201532011	김영우	1	010-2090-1421	남	kim112@skhu.ac.kr	소프트웨어공학과  Student
    7	201532012	주한요	3	010-4624-1467	남	joo124@skhu.ac.kr	정보통신공학과   Student
    8	201532013	김숙홍	1	010-3791-1522	남	kim133@skhu.ac.kr	소프트웨어공학과   Student
    9	201532014	홍영수	4	010-2897-1525	남	hong142@skhu.ac.kr	글로컬IT공학과    Student
    10	201532015	박원종	1	010-7655-1724	남	park157@skhu.ac.kr	소프트웨어공학과    Student
    11	201432015	변준호	1	010-2245-1750	남	byeon152@skhu.ac.kr	소프트웨어공학과    Student 
    12	201532016	고희정	3	010-5691-1943	여	ko165@skhu.ac.kr	정보통신공학과     Student
    13	201432016	신철대	4	010-3221-1956	남	sin163@skhu.ac.kr	글로컬IT공학과    Student
    14	201532017	서윤정	3	010-4310-1965	여	seo174@skhu.ac.kr	정보통신공학과    Student
    15	201432017	오화순	3	010-8527-2048	여	oh178@skhu.ac.kr	정보통신공학과    Student
    */
    @GetMapping("edit")
    public String edit(Model model,  Pagination pagination, @RequestParam("id") int id) {
        Student student = studentMapper.findById(id);
        StudentEdit studentEdit = modelMapper.map(student, StudentEdit.class);
        model.addAttribute("studentEdit", studentEdit);
        model.addAttribute("departments", departmentMapper.findAll());
        
        // layout.html로 렌더링하고, 'view'에 사용할 프래그먼트 경로 넘기기
        model.addAttribute("view", "student/edit");
        
        return "layout"; // layout.html로 렌더링
    }

    @PostMapping(value="edit", params="cmd=save")
    public String edit(Model model, Pagination pagination,
            @Valid StudentEdit studentEdit, BindingResult bindingResult) {
        try {
            log.debug(studentEdit.toString());
            if (bindingResult.hasErrors())
                throw new Exception("저장할 수 없습니다");
            Student student = modelMapper.map(studentEdit, Student.class);
            studentMapper.update(student);
            return "redirect:list?" + pagination.getQueryString();
        }
        catch (Exception ex) {
            bindingResult.reject("", null, ex.getMessage());
            model.addAttribute("departments", departmentMapper.findAll());
            
            // layout.html로 렌더링하고, 'view'에 사용할 프래그먼트 경로 넘기기
            model.addAttribute("view", "student/edit");
            
            return "layout"; // layout.html로 렌더링
           
        }
    }
    //삭제 버튼을 클릭했을 때, 이 메소드가 호출된다.
    @PostMapping(value="edit", params="cmd=delete")
    public String delete(Model model, Pagination pagination,
            @Valid StudentEdit studentEdit, BindingResult bindingResult) {
        try {
            studentMapper.deleteById(studentEdit.getId());
            return "redirect:list?" + pagination.getQueryString();
        }
        catch (Exception ex) {
        	
            bindingResult.reject("", null, ex.getMessage());
            
            model.addAttribute("departments", departmentMapper.findAll());
            
            // layout.html로 렌더링하고, 'view'에 사용할 프래그먼트 경로 넘기기
            model.addAttribute("view", "student/edit");
            return "layout"; // layout.html로 렌더링
            
        }
    }

    @GetMapping("create")
    public String create(Model model, Pagination pagination) {
        StudentEdit studentEdit = new StudentEdit();
        model.addAttribute("studentEdit", studentEdit);
        model.addAttribute("departments", departmentMapper.findAll());
        
        // layout.html로 렌더링하고, 'view'에 사용할 프래그먼트 경로 넘기기
        model.addAttribute("view", "student/edit");
        
        return "layout"; // layout.html로 렌더링
    }

    
/*
새 학생 레코드를 DB에 insert한 후, 학생 목록 페이지로 나갈 때,
방금 등록한 새 학생 객체가 보이는 마지막 페이지로 나가야 한다.

int lastPage = (int)Math.ceil((double)studentMapper.count() / pagination.getSz());
  마지막 페이지 번호는 위와 같이 계산한다.
  Math.ceil(double value) 메소드는 value에 소수점 자리가 있을 경우, 올림값을 리턴한다.
  (예: value 값이 3.14일 경우, 4를 리턴)

pagination.setPg(lastPage);
  현재 페이지 번호를 마지막 페이지 번호로 변경한다.    
 */
    @PostMapping("create")
    public String create(Model model, Pagination pagination,
            @Valid StudentEdit studentEdit, BindingResult bindingResult) {
        try {
            log.debug(studentEdit.toString());
            if (bindingResult.hasErrors())
                throw new Exception("저장할 수 없습니다");
            Student student = modelMapper.map(studentEdit, Student.class);
            studentMapper.insert(student);
            int lastPage = (int)Math.ceil((double)studentMapper.getCount() / pagination.getSz());
            pagination.setPg(lastPage);
            return "redirect:list?" + pagination.getQueryString();
        }
        catch (Exception ex) {
            bindingResult.reject("", null, ex.getMessage());
            model.addAttribute("departments", departmentMapper.findAll());
            
            // layout.html로 렌더링하고, 'view'에 사용할 프래그먼트 경로 넘기기
            model.addAttribute("view", "student/edit");
            
            return "layout"; // layout.html로 렌더링
            
        }
    }
}



