package com.example.demo.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Loans;

public interface LoanRepo extends CrudRepository<Loans, Integer>
{
	List<Loans> findByCustomerIdOrderByStartDtDesc(int customerId);
}
