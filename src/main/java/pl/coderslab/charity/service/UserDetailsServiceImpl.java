package pl.coderslab.charity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.model.Role;
import pl.coderslab.charity.model.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private UserService userService;
	
	@Autowired
	public void setUserRepository(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		User user = userService.findUserByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException(email);
		}
		List<GrantedAuthority> roles = getUserAuthority(user.getRoles());
		return setUserForAuthentication(user, roles);
	}
	
	private UserDetails setUserForAuthentication(User user, List<GrantedAuthority> roles) {
		return new org.springframework.security.core.userdetails.User
				(user.getEmail(), user.getPassword(), true, true, true, true, roles);
	}
	
	private List<GrantedAuthority> getUserAuthority(Set<Role> roles) {
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		for (Role role: roles){
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>(authorities);
		return grantedAuthorities;
	}
}
