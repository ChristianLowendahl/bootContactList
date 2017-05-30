package se.prodentus.contact_list;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactListController {
	
	@Autowired
	private ContactListRepository contactListRepository;
	
	@RequestMapping("/")
	public String index() {
		return "Welcome to Spring Boot REST!";
	}
	
	@RequestMapping(value="/contacts/", method=RequestMethod.GET)
	public List<Contact> listContacts(
			@RequestParam(required=false) String firstName,
			@RequestParam(required=false) String lastName,
			@RequestParam(required=false) String firstNameSearchWord,
			@RequestParam(required=false) String lastNameSearchWord
			) {
		List<Contact> contacts = null;
		if (firstName != null) {
			contacts = contactListRepository.findByFirstNameIgnoreCase(firstName);
		} else if (lastName != null) {
			contacts = contactListRepository.findByLastNameIgnoreCase(lastName);
		} else if (firstNameSearchWord != null) {
			contacts = contactListRepository.findByFirstNameContainingIgnoreCase(firstNameSearchWord);
		} else if (lastNameSearchWord != null) {
			contacts = contactListRepository.findByLastNameContainingIgnoreCase(lastNameSearchWord);
		} else {
			contacts = contactListRepository.findAllByOrderByIdAsc();
		}
		return contacts;
	}
	
	@RequestMapping(value="/contacts/{id}", method=RequestMethod.GET)
	public Contact getContact(@PathVariable("id") Long id) {
		return contactListRepository.findOne(id);
	}
	
	@RequestMapping(value="/contacts/", method=RequestMethod.POST)
	// @ResponseStatus(value=HttpStatus.CREATED)
	public ResponseEntity<Void> addContact(@RequestBody Contact contact) throws URISyntaxException {
		contactListRepository.save(contact);
		HttpHeaders headers = new HttpHeaders();
		URI uri = new URI("/contacts/" + contact.getId());
		headers.setLocation(uri);
		ResponseEntity<Void> responseEntity = new ResponseEntity<>(headers, HttpStatus.CREATED);
		return responseEntity;
	}
	
	@RequestMapping(value="/contacts/{id}", method=RequestMethod.DELETE)
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	public void deleteContact(@PathVariable Long id) {
		contactListRepository.delete(id);
	}
	
}