package net.skhu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import net.skhu.mapper.SugangMapper;

/*
	@RequiredArgsConstructor 어노테이션을 붙이면

		@Autowired <-- 어노테이션을 붙이지 않아도 아래처럼 생성자를 자동으로 만들어준다.
		SugangMapper sugangMapper;

	public SugangController(SugangMapper sugangMapper) {
		this.sugangMapper = sugangMapper;
	}
*/


@Controller
public class SugangController {

	@Autowired
	SugangMapper sugangMapper;


	//수강정보 리스트 조회 요청
	//GET http://localhost:8088/sugang/list
	@GetMapping("sugang/list")
	public String list(Model model) {
					  //디스패처 서블릿이 @ModelAttribute를 만들어줌

		model.addAttribute("sugangs", sugangMapper.findAll());
									  //List<Sugang>
		return "sugang/list"; //뷰 경로

	}






}
