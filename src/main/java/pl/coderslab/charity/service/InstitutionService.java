package pl.coderslab.charity.service;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.model.Institution;
import pl.coderslab.charity.repository.InstitutionRepository;

import java.util.List;

@Service
public class InstitutionService {
	
	private InstitutionRepository institutionRepo;
	
	public List<Institution> getInstitutions(){
		return institutionRepo.findAll();
	}
}
