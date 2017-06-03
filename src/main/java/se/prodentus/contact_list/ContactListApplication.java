package se.prodentus.contact_list;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ContactListApplication implements CommandLineRunner {
	
	@Autowired
	private ContactListRepository contactListRepository;

	public static void main(String[] args) {
		SpringApplication.run(ContactListApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		contactListRepository.save(new Contact("Anders", "Andersson", "010-111 11 11", "anders@prodentus.se"));
		contactListRepository.save(new Contact("Berit", "Bengtsson", "020-222 22 22", "berit@prodentus.se"));
		contactListRepository.save(new Contact("Carl", "Carlsson", "030-333 33 33", "carl@prodentus.se"));
		contactListRepository.save(new Contact("Diana", "Davidsson", "040-444 44 44", "diana@prodentus.se"));
	}
	
	/**
	// This works but it is nicer to implement the interface CommandLineRunner
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext context, ContactListRepository contactListRepository) {
		return args -> {
			contactListRepository.save(new Contact("Anders", "Andersson", "010-111 11 11", "anders@prodentus.se"));
			contactListRepository.save(new Contact("Berit", "Bengtsson", "020-222 22 22", "berit@prodentus.se"));
			contactListRepository.save(new Contact("Carl", "Carlsson", "030-333 33 33", "carl@prodentus.se"));
			contactListRepository.save(new Contact("Diana", "Davidsson", "040-444 44 44", "diana@prodentus.se"));
		};
	}
	
	*/
	
}
