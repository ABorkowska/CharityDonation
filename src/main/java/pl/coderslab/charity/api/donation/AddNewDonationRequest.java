package pl.coderslab.charity.api.donation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.coderslab.charity.model.Institution;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddNewDonationRequest {
	
	private Integer quantity;
	private List<String> categories;
	private String institution;
	private String street;
	private String city;
	private String postcode;
	private String phone;
	private LocalDate date;
	private LocalTime time;
	private String comment;
}
