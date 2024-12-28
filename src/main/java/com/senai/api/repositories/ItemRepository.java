package com.senai.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senai.api.entities.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

	Optional<Item> findByTitle(String title);

}