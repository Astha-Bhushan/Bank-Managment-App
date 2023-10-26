package com.example.demo.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Cards;

@Repository
public interface CardsRepo extends CrudRepository<Cards, Long> {
	
	List<Cards> findByCustomerId(int customerId);

}
