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
	        
	        // layout.html로 렌더링하고, 'view'에 사용할 프래그먼트 경로 넘기기
	        model.addAttribute("view", "index");

	        return "layout";  // layout.html 을 렌더링
	    }
}
