package cat.institutmarianao.closetws.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import cat.institutmarianao.closetws.repositories.UserRepository;

public class UserDetailsImpl implements UserDetailsService{

	@Autowired
	private UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repository.findById(username).orElseThrow(()->new UsernameNotFoundException("User not found"));
	}

}
