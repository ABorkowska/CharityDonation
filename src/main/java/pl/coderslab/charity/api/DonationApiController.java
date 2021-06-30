package pl.coderslab.charity.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.charity.api.donation.AddNewDonationRequest;
import pl.coderslab.charity.model.Category;
import pl.coderslab.charity.model.Donation;
import pl.coderslab.charity.service.DonationService;

import java.util.stream.Collectors;

@RestController
public class DonationApiController {
	
	private final DonationService donationService;
	
	public DonationApiController(DonationService donationService) {
		this.donationService = donationService;
	}
	
	//
	@PostMapping("/api/donation/add")
	public ResponseEntity addDonation(@RequestBody AddNewDonationRequest addNewDonationRequest) {
		System.out.println(addNewDonationRequest);
		
		Donation donation = donationService.addDonation(
				addNewDonationRequest.getCategories(),
				addNewDonationRequest.getQuantity(),
				addNewDonationRequest.getInstitution(),
				addNewDonationRequest.getStreet(),
				addNewDonationRequest.getCity(),
				addNewDonationRequest.getPostcode(),
				addNewDonationRequest.getPhone(),
				addNewDonationRequest.getDate(),
				addNewDonationRequest.getTime(),
				addNewDonationRequest.getComment());
		
		return ResponseEntity.ok(donation);
	}
}
