package br.com.erudio.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.data.model.Books;
import br.com.erudio.data.vo.v1.BooksVO;
import br.com.erudio.exception.ResourceNotFoundException;
import br.com.erudio.repository.BooksRepository;

@Service
public class BooksServices {
	
	@Autowired
	BooksRepository repository;
	
	private Books fromBooksVO(BooksVO booksVO) {
		Books books = new Books();
		books.setId(booksVO.getId());
		books.setAuthor(booksVO.getAuthor());
		books.setLaunchDate(booksVO.getLaunchDate());
		books.setPrice(booksVO.getPrice());
		books.setTitle(booksVO.getTitle());
		
		return books;
	}
	
	public BooksVO create (BooksVO books) {
		Books entity = fromBooksVO(books);
		repository.save(entity);
		return new BooksVO(entity);
	}
	
	public BooksVO update (BooksVO books) {
		Books entity = repository.findById(books.getId()).orElseThrow(() -> 
		new ResourceNotFoundException("No records found this ID"));
		
		entity.setAuthor(books.getAuthor());
		entity.setLaunchDate(books.getLaunchDate());
		entity.setPrice(books.getPrice());
		entity.setTitle(books.getTitle());
		
		repository.save(entity);
		
		return new BooksVO(entity);
	}
	
	public void delete (Long id) {
		Books entity = repository.findById(id).orElseThrow(() -> 
		new ResourceNotFoundException("No records found this ID"));
		
		repository.delete(entity);
	}
	
	public BooksVO findById (Long id) {
		Books entity = repository.findById(id).orElseThrow(() -> 
		new ResourceNotFoundException("No records found this ID"));
		
		return new BooksVO(entity);
	}
	
	public List<BooksVO> findAll(){
		List<Books> booksList = repository.findAll();
		
		return booksList.stream().map(b -> new BooksVO(b)).collect(Collectors.toList());
	}

}
