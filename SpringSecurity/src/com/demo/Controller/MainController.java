package com.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.Model.User;
import com.demo.repository.UserRepository;

@Controller
public class MainController {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;

	@RequestMapping("/")
	public String showHome() {
		return "home";
	}

	@RequestMapping("/show-report")
	public String showReport() {
		return "report";
	}

	@RequestMapping("/show-users")
	public String showUsers() {
		return "users";
	}

	@RequestMapping("/sign-up")
	public String showSignUp(Model model) {
		User user = new User();
		user.setName("ron");
		user.setUsername("ron");
		user.setPassword(passwordEncoder.encode("1234"));
		user.setRole("USER");
		// db.insertUser(user);
		User userDB = userRepository.save(user);
		System.out.println(userDB);
		model.addAttribute("user", user);
		return "home";
	}
}
