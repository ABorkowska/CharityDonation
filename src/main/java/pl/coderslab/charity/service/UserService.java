package pl.coderslab.charity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.model.Role;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.repository.RoleRepository;
import pl.coderslab.charity.repository.UserRepository;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserService {
	
	private UserRepository userRepo;
	private RoleRepository roleRepo;
	private BCryptPasswordEncoder passEncoder;

@Autowired
public UserService(UserRepository userRepo, RoleRepository roleRepo,BCryptPasswordEncoder passEncoder) {
	this.userRepo = userRepo;
	this.roleRepo = roleRepo;
	this.passEncoder = passEncoder;
}
	public User findUserByEmail(String email) {
		return userRepo.findByEmail(email);
	}
	public void addUser(String name, String surname, String email, String password) {
		User user = new User();
		user.setName(name);
		user.setSurname(surname);
		user.setEmail(email);
		user.setPassword(passEncoder.encode(password));
		user.setEnabled(1);
		Role role = roleRepo.findByName("USER");
		user.setRoles(new HashSet<Role>(Arrays.asList(role)));
		userRepo.save(user);
	}
}
