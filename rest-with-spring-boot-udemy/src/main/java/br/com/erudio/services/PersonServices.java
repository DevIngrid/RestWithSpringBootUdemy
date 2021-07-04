package br.com.erudio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.exception.ResourceNotFoundException;
import br.com.erudio.model.Person;
import br.com.erudio.repository.PersonRepository;

@Service
public class PersonServices {
	
	@Autowired
	PersonRepository repository;
	
	//private final AtomicLong counter = new AtomicLong();
	
	public Person create(Person person) {
		repository.save(person);
		return person;
	}
	
	public Person update(Person person) {
		Person entity = repository.findById(person.getId()).orElseThrow(() -> 
		new ResourceNotFoundException("No records found this ID"));
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		return repository.save(entity);
	}
	
	public void delete (Long id) {
		Person entity = repository.findById(id).orElseThrow(() -> 
		new ResourceNotFoundException("No records found this ID"));
		
		repository.delete(entity);
		
	}
	
	/*public Person findById(String id) {
		Person person = new Person(counter.incrementAndGet(),"Leandro", "Costa", 
				"Uberlandia - Minas Gerais - Brasil","Male");
		
		return person;
		
	}*/
	
	public Person findById(Long id) {
		return repository.findById(id).orElseThrow(() -> 
		new ResourceNotFoundException("No records found this ID"));
	}
	
	public List<Person> findAll() {
		/*List<Person> persons = new ArrayList<>();
		for (int i = 0; i <8; i++) {
			Person person = mockPerson(i);
			persons.add(person);
		}
		return persons;*/
		return repository.findAll();
	}

	/*private Person mockPerson(int i) {
		Person person = new Person(counter.incrementAndGet(),"Person name" +i, "Last name"+i, 
				"Some address in Brasil"+i,i%2==0? "Male":"Fem");
		return person;
	}*/

}
