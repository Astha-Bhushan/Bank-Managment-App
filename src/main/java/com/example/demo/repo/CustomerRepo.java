package com.example.demo.repo;

import com.example.demo.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepo extends CrudRepository<Customer, Integer> {

    List<Customer> findByEmail(String email);
    
}
