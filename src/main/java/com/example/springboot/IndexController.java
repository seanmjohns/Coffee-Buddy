package com.example.springboot;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class IndexController {

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@PostMapping("/")
	public RedirectView handleFormSubmit(@RequestParam Map<String, String> formData) {
		// Process form data here
		System.out.println(formData);

		return new RedirectView("/places");
	}
}
