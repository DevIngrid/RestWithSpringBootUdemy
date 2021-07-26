package br.com.erudio.controller;

import static org.springframework.http.ResponseEntity.ok;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.erudio.data.model.User;
import br.com.erudio.repository.UserRepository;
import br.com.erudio.security.AccountCredentialsVO;
import br.com.erudio.security.jwt.JwtTokenProvider;
import br.com.erudio.services.UserServices;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {
	
	//@Autowired
	//AuthenticationManager authenticationManager;
	
	
	@Autowired
	JwtTokenProvider tokenProvider;
	
	//@Autowired
	//UserRepository repository;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	UserServices services;
	
	
	@Operation(summary = "Authenticate a user by credentials")
	@PostMapping(value="/signin", produces = {"application/json", "application/xml", "application/x-yaml"},
				consumes = {"application/json", "application/xml", "application/x-yaml"})
	public ResponseEntity signin(@RequestBody AccountCredentialsVO data) {
		try {
			
			String username = data.getUsername();
			String pasword = data.getPassword();
			
			//authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, pasword));
			
			//User user = repository.findByUsername(username);
			
			//System.out.println("teste de user: " + user);
			
			
			
			String token = "";
			
			/*if (user != null) {
				boolean senhasBatem = passwordEncoder.matches(pasword,user.getPassword());
				if(senhasBatem) {
					token = tokenProvider.createToken(username, user.getRoles());
				}else {
					throw new BadCredentialsException("Invalid username/password supplied!");
				}
				
			} else {
				throw new UsernameNotFoundException("Username " + username + " not found!");	
			}*/
			
			User usuarioAutenticado = (User) services.autenticar(data);
			token = tokenProvider.createToken(username, usuarioAutenticado.getRoles());
			
			
			Map<Object, Object> model = new HashMap<>();
			model.put("username", username);
			model.put("token", token);
			
			return ok(model);
			
					
		} catch (UsernameNotFoundException e) {
			//throw new BadCredentialsException("Invalid username/password supplied!");
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
			}
	}

}
