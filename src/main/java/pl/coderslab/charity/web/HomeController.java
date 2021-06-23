package pl.coderslab.charity.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.coderslab.charity.model.Institution;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;

import java.util.List;

@Controller
public class HomeController {
    
    private final InstitutionService instService;
    private final DonationService donationService;
    
    public HomeController(InstitutionService instService, DonationService donationService) {
        this.instService = instService;
        this.donationService = donationService;
    }
    
    @ModelAttribute ("institutions")
    public List<Institution> institutions() {
        return instService.getInstitutions();
    }
    
    @GetMapping("/home")
    public String homeAction(Model model){
        Integer amount = donationService.getPackageAmount();
        Integer total = donationService.getTotalDonations();
        model.addAttribute("amount", amount);
        model.addAttribute("total", total);
        return "index";
    }
}
