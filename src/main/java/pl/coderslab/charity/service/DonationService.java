package pl.coderslab.charity.service;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.repository.DonationRepository;

@Service
public class DonationService {

	private final DonationRepository donationRepo;
	
	public DonationService(DonationRepository donationRepo) {
		this.donationRepo = donationRepo;
	}
	
	public Integer getPackageAmount(){
		return donationRepo.getAmount();
	}
	public Integer getTotalDonations(){
		return donationRepo.getDonations();
	}
}
