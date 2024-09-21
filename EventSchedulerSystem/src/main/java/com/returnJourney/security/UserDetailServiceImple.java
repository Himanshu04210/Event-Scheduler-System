package com.returnJourney.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.returnJourney.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImple implements UserDetailsService{
	@Autowired
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 Optional<com.returnJourney.model.User> optUser = userRepository.findByEmail(username);
		 if(optUser.isPresent()) {
				com.returnJourney.model.User user= optUser.get();
				List<GrantedAuthority> authorities= new ArrayList<>();
				SimpleGrantedAuthority sga= new SimpleGrantedAuthority(user.getRole());
				authorities.add(sga);
				return new User(user.getEmail(), user.getPassword(), authorities);
		 }
		 throw new BadCredentialsException("User Details not found with this username: "+username);
	}
}
