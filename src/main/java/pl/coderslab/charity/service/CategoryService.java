package pl.coderslab.charity.service;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.model.Category;
import pl.coderslab.charity.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {
	
	private final CategoryRepository catRepo;
	
	
	public CategoryService(CategoryRepository catRepo) {
		this.catRepo = catRepo;
	}
	
	public List<Category> getCategories(){
		return catRepo.findAll();
	}
}
