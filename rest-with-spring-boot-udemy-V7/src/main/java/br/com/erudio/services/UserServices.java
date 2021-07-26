package br.com.erudio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.erudio.repository.UserRepository;
import br.com.erudio.security.AccountCredentialsVO;

@Service
public class UserServices implements UserDetailsService{
	
	@Autowired
	UserRepository repository;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	public UserServices(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var user = repository.findByUsername(username);//var é um User
		if (user != null) {
			return user;
		} else {
			throw new UsernameNotFoundException("Username " + username + " not found");
		}
		
	}
	
	//fiz para testar e deu certo
	public UserDetails autenticar (AccountCredentialsVO data) {
		UserDetails user = loadUserByUsername(data.getUsername());
		boolean senhasBatem = passwordEncoder.matches(data.getPassword(),user.getPassword());
		if (senhasBatem) {
			return user;
		}
		throw new BadCredentialsException("Invalid password supplied!");
	}

}
