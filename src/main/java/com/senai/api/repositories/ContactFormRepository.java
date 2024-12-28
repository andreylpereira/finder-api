package com.senai.api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senai.api.entities.ContactForm;

@Repository
public interface ContactFormRepository extends JpaRepository<ContactForm, Integer> {

	Optional<ContactForm> findByNameUser(String nameUser);
	List<ContactForm> findByItemId(Integer itemId);

}
