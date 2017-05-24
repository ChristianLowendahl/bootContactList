package se.prodentus.contact_list;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactListController {
	
	@Autowired
	private ContactListRepository contactListRepository;
	
	@RequestMapping("/")
	public String index() {
		return "Hello from Christian's first spring boot!";
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
			contacts = contactListRepository.findAll();
		}
		return contacts;
	}
	
	@RequestMapping(value="/contacts/", method=RequestMethod.POST)
	public void addContact(@RequestBody Contact contact) {
		contactListRepository.save(contact);
	}
	
	@RequestMapping(value="/contacts/{id}", method=RequestMethod.DELETE)
	public void deleteContact(@PathVariable Long id) {
		contactListRepository.delete(id);
	}
	
}