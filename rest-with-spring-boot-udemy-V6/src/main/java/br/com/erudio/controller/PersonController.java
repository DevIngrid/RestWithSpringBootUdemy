package br.com.erudio.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.services.PersonServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Person Endpoint")
@RestController
@RequestMapping("/api/person/v1")
public class PersonController {
	
	@Autowired
	private PersonServices services; 
	
	@Operation(summary = "Find all people")
	@GetMapping (produces = {"application/json", "application/xml", "application/x-yaml"})
	public List<PersonVO> findAll() {
		List<PersonVO> persons = services.findAll();
		persons
		.stream()
		.forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
		return persons;
	}
	
	@Operation(summary = "Find a specific person by your ID")
	@GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
	public PersonVO findById(@PathVariable("id") Long id) {
		
		PersonVO personVO = services.findById(id);
		personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return personVO;
	}
	
	@Operation(summary = "Create a new person")
	@PostMapping(produces = {"application/json", "application/xml","application/x-yaml"},
				consumes = {"application/json", "application/xml", "application/x-yaml"})
	public PersonVO create(@RequestBody PersonVO person) {
		PersonVO personVO = services.create(person);
		personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getKey())).withSelfRel());
		return personVO;
	}
	
	@Operation(summary = "Update a specific person")
	@PutMapping (produces = {"application/json", "application/xml","application/x-yaml"},
				consumes = {"application/json", "application/xml", "application/x-yaml"})
	public PersonVO update(@RequestBody PersonVO person) {
		PersonVO personVO = services.update(person);
		personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getKey())).withSelfRel());
		return personVO;
	}
	
	@Operation(summary = "Delete a specific a book by your ID")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		services.delete(id);
		return ResponseEntity.ok().build();
	}
	
}
	
	