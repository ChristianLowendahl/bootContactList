package se.prodentus.contact_list;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ContactListApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContactListApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext context, ContactListRepository contactListRepository) {
		return args -> {
			Contact contact1 = new Contact(new Long(1), "Anders", "Andersson", "010-111 11 11", "anders@prodentus.se");
			contactListRepository.save(contact1);
			Contact contact2 = new Contact(new Long(2), "Berit", "Bengtsson", "020-222 22 22", "berit@prodentus.se");
			contactListRepository.save(contact2);
			Contact contact3 = new Contact(new Long(3), "Carl", "Carlsson", "030-333 33 33", "carl@prodentus.se");
			contactListRepository.save(contact3);
			Contact contact4 = new Contact(new Long(4), "Diana", "Davidsson", "040-444 44 44", "diana@prodentus.se");
			contactListRepository.save(contact4);
		};
	}
	
}
