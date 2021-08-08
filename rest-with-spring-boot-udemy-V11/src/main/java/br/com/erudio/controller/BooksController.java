package br.com.erudio.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.data.vo.v1.BooksVO;
import br.com.erudio.services.BooksServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Book Endpoint")
@RestController
@RequestMapping("/api/books/v1")
public class BooksController {
	
	@Autowired
	private BooksServices service;
	
	/*@Operation(summary = "Find all books")
	@GetMapping (produces = {"application/json", "application/xml", "application/x-yaml"})
	public List<BooksVO> findAll(){
		List<BooksVO> listBooks = service.findAll();
		listBooks.stream()
		.forEach(b -> b.add(linkTo(methodOn(BooksController.class).findById(b.getId())).withSelfRel()));
		return listBooks;
	}*/
	
	//modifiquei o findAll para paginar
	
	@Operation(summary = "Find all books")
	@GetMapping (produces = {"application/json", "application/xml", "application/x-yaml"})
	public Page<BooksVO> findAll(@RequestParam(value="page", defaultValue="0") int page,
			@RequestParam(value="limit", defaultValue="12") int limit,
			@RequestParam(value="direction", defaultValue="asc") String direction){
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "author"));
		
		Page<BooksVO> listBooks = service.findAll(pageable);
		listBooks
		.forEach(b -> b.add(linkTo(methodOn(BooksController.class).findById(b.getId())).withSelfRel()));
		return listBooks;
	}
	
	@Operation(summary = "Find a specific book by your ID")
	@GetMapping(value="/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
	public BooksVO findById(@PathVariable ("id") Long id) {
		BooksVO books = service.findById(id);
		books.add(linkTo(methodOn(BooksController.class).findById(id)).withSelfRel());
		return books;
	}
	
	@Operation(summary = "Create a new book")
	@PostMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
				consumes = {"application/json", "application/xml", "application/x-yaml"})
	public BooksVO create(@RequestBody BooksVO books) {
		BooksVO booksVO = service.create(books);
		return booksVO.add(linkTo(methodOn(BooksController.class).findById(books.getId())).withSelfRel());
	}
	
	@Operation(summary = "Update a specific book")
	@PutMapping (produces = {"application/json", "application/xml", "application/x-yaml"},
			     consumes = {"application/json", "application/xml", "application/x-yaml"})
	public BooksVO update (@RequestBody BooksVO books) {
		BooksVO booksVO = service.update(books);
		return booksVO.add(linkTo(methodOn(BooksController.class).findById(booksVO.getId())).withSelfRel());
	}
	
	@Operation(summary = "Delete a specific book by your ID")
	@DeleteMapping(value="/{id}")
	public ResponseEntity<?> delete (@PathVariable ("id")Long id){
		service.delete(id);
		return ResponseEntity.ok().build();
		
	}

}
