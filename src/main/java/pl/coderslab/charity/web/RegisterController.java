package pl.coderslab.charity.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.service.UserService;

@Controller
public class RegisterController {
	
	private final UserService userService;
	
	public RegisterController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/donation/register")
	public String showRegisterForm(Model model, @RequestParam(required = false) String error) {
		if (error != null) {
			model.addAttribute("error", "Podane hasła muszą byc identyczne");
		}
		return "register";
	}
	
	@PostMapping("/donation/register")
	public String registerUser(
			@RequestParam String name,
			@RequestParam String surname,
			@RequestParam String email,
			@RequestParam String password,
			@RequestParam String password2) {
		
		
		if (!password.equals(password2)) {
			return "redirect:/donation/register?error=true";
		}
		userService.addUser(name, surname, email, password);
		return "redirect:/donation/login";
	}
	
	@GetMapping("/donation/login")
	public String showLoginForm(Model model, @RequestParam(required = false) String error) {
		User user = new User();
		if (error != null) {
			model.addAttribute("error", "Nieprawidłowy login i/lub hasło");
		}
		model.addAttribute("user", user);
		return "login";
	}
}
