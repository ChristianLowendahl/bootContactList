package se.prodentus.contact_list;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactListRepository extends JpaRepository<Contact, Long>{
	List<Contact> findByFirstNameIgnoreCase(String firstName);
	List<Contact> findByLastNameIgnoreCase(String lastName);
	List<Contact> findByFirstNameContainingIgnoreCase(String firstNameSearchWord);
	List<Contact> findByLastNameContainingIgnoreCase(String lastNameSearchWord);
	List<Contact> findAllByOrderByIdAsc();
}