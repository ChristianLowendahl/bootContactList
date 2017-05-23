package se.prodentus.contact_list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactListController {
	
	@RequestMapping("/")
	public String index() {
		return "Hello from Christian's first spring boot!";
	}
	
	@RequestMapping(value="/contacts/", method=RequestMethod.GET)
	public List<Contact> listContacts(@RequestParam(required=false) String email) {
		Contact contact = new Contact(new Long(1), "Christian", "Löwendahl", "076-550 66 98", "christian@prodentus.se");
		return new ArrayList<>(Arrays.asList(contact));
	}
	
	@RequestMapping(value="/contacts/", method=RequestMethod.POST)
	public void addContact(@RequestBody Contact contact) {
		System.out.println(contact.getFirstName());
	}
	
	@RequestMapping(value="/contacts/{id}", method=RequestMethod.DELETE)
	public void deleteContact(@PathVariable Long id) {
		System.out.println(id);
	}
	
}