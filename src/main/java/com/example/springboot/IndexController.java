package com.example.springboot;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class IndexController {

	/* 
	@GetMapping("/")
	public String index() {
		return "index";
	}*/

	@GetMapping("/login")
	public String login() {
		return "login"; // Return login view (login.html)
	}

	@GetMapping("/register")
	public String register() {
		return "register"; // Return register view (register.html)
	}

	/**
	 * Handles POST requests from the login form.
	 */
	@PostMapping("/login")
	public RedirectView handleLoginFormSubmit(@RequestParam Map<String, String> formData) {
		// Process form data here
		System.out.println("Problem");
		System.out.println(formData);

		// Redirect to the /places page after processing login data
		return new RedirectView("/places");
	}
	
}
