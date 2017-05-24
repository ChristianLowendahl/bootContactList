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
			/**
			System.out.println("Beans provided by Spring Boot:");
			String[] beanNames = context.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for(String beanName : beanNames) {
				System.out.println(beanName);
			}
			*/
			
			Contact contact = new Contact(new Long(1), "Christian", "Löwendahl", "076-550 66 98", "christian@prodentus.se");
			contactListRepository.save(contact);
		};
	}
}
