package pl.coderslab.charity.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.charity.model.Category;
import pl.coderslab.charity.model.Donation;
import pl.coderslab.charity.model.Institution;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.service.CategoryService;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;
import pl.coderslab.charity.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
public class DonationController {
	
	private final DonationService donationService;
	private final InstitutionService instService;
	private final CategoryService catService;
	private final UserService userService;
	
	
	public DonationController(DonationService donationService, InstitutionService instService, CategoryService catService, UserService userService) {
		this.donationService = donationService;
		this.instService = instService;
		this.catService = catService;
		this.userService = userService;
	}
	
	@GetMapping ("/donation/add")
	public String showDonationForm(Model model, Principal principal){
		if (principal==null) {
			return "redirect:/donation/login";
		}
		User user = userService.findUserByEmail(principal.getName());
		List<Category> categories = catService.getCategories();
		List<Institution> institutions = instService.getInstitutions();
		model.addAttribute("categories", categories);
		model.addAttribute("institutions", institutions);
		model.addAttribute("user", user);
		
		return "donation-form";
	}
	
}
