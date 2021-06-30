package pl.coderslab.charity.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "donations")
@Entity
public class Donation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer quantity;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name = "donations_categories",
			joinColumns = @JoinColumn(name="donation_id"),
			inverseJoinColumns = @JoinColumn(name="category_id"))
	private List<Category> categories = new ArrayList<>();
	
	@ManyToOne
	private Institution institution;
	
	@NotBlank
	private String street;
	@NotBlank
	private String city;
	
	@NotBlank
	@Pattern(regexp="^[0-9]{2}-[0-9]{3}$")
	private String zipCode;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate pickUpDate;
	
	@DateTimeFormat(pattern= "^([1-9]|[01][1-9]|2[1-3]):([1-9]|[0-5][1-9])")
	private LocalTime pickUpTime;
	
	@Nullable
	private String pickUpComment;
	
	@NotNull
	private String phone;
	
}