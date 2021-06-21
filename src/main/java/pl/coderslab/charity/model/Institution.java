package pl.coderslab.charity.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Table(name = "institutions")
@Entity
public class Institution {
	@Column(name = "ID", nullable = false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	
	@OneToMany
	private List<Donation> donations;
}