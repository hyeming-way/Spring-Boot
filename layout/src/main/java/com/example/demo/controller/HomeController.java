package com.example.demo.controller;

import com.example.demo.model.HomePageData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        // 여러 개의 HomePageData 객체 생성
        List<HomePageData> dataList = Arrays.asList(
            new HomePageData("홍길동", "소프트웨어 엔지니어", "안녕하세요! 저는 소프트웨어 엔지니어 홍길동입니다.", "010-1234-5678", "example1@gmail.com", "www.example1.com"),
            new HomePageData("이순신", "프로젝트 매니저", "프로젝트 매니저 이순신입니다.", "010-2345-6789", "example2@gmail.com", "www.example2.com"),
            new HomePageData("강감찬", "디자인 전문가", "디자인 전문가 강감찬입니다.", "010-3456-7890", "example3@gmail.com", "www.example3.com"),
            new HomePageData("유관순", "마케팅 전략가", "마케팅 전략가 유관순입니다.", "010-4567-8901", "example4@gmail.com", "www.example4.com")
        );

        // 모델에 데이터 추가
        model.addAttribute("dataList", dataList);
        return "home"; // home.html로 이동
    }
}
