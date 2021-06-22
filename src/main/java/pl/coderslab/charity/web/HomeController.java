package pl.coderslab.charity.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.coderslab.charity.model.Institution;
import pl.coderslab.charity.service.InstitutionService;

import java.util.Collection;
import java.util.List;

@Controller
public class HomeController {
    
    private final InstitutionService instService;
    
    public HomeController(InstitutionService instService) {
        this.instService = instService;
    }
    
    @ModelAttribute ("institutions")
    public List<Institution> institutions() {
        return instService.getInstitutions();
    }
    
    @GetMapping("/home")
    public String homeAction(Model model){
        return "index";
    }
}
