package pl.coderslab.charity.service;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.model.Category;
import pl.coderslab.charity.model.Donation;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DonationService {

	private final DonationRepository donationRepo;
	private final CategoryRepository catRepo;
	private final InstitutionRepository instRepo;
	
	public DonationService(DonationRepository donationRepo, CategoryRepository catRepo, InstitutionRepository instRepo) {
		this.donationRepo = donationRepo;
		this.catRepo = catRepo;
		this.instRepo = instRepo;
	}
	
	public Integer getPackageAmount(){
		return donationRepo.getAmount();
	}
	public Integer getTotalDonations(){
		return donationRepo.getDonations();
	}
	public Donation addDonation(List<String> categories,
	                            Integer quantity,
	                            String institution,
	                            String street, String city, String postcode, String phone,
	                            LocalDate date, LocalTime time, String comment){
		Donation donation = new Donation();
		donation.setCategories(categories.stream().map(cat -> {
			Category category = catRepo.findByName(cat);
			return category;
		}).collect(Collectors.toList()));
		donation.setQuantity(quantity);
		donation.setInstitution(instRepo.findByName(institution));
		donation.setStreet(street);
		donation.setCity(city);
		donation.setZipCode(postcode);
		donation.setPhone(phone);
		donation.setPickUpTime(time);
		donation.setPickUpDate(date);
		donation.setPickUpComment(comment);
		
		return donationRepo.save(donation);
	}
}
