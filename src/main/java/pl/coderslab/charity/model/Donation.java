package pl.coderslab.charity.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "donations")
@Entity
public class Donation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer quantity;
	
	@ManyToMany
	@JoinTable(name = "donations_categories",
			joinColumns = @JoinColumn(name="donation_id"),
			inverseJoinColumns = @JoinColumn(name="category_id"))
	private List<Category> categories = new ArrayList<>();
	
	@ManyToOne
	private Institution institution;
	
	private String street;
	private String city;
	private String zipCode;
	private LocalDate pickUpDate;
	private LocalTime pickUpTime;
	private String pickUpComment;
	
}