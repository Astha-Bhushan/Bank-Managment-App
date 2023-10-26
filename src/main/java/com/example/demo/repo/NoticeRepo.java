package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Notices;

public interface NoticeRepo extends CrudRepository<Notices, Integer>
{
	@Query(value = "from Notice n where CURDATE() BETWEEN noticBegDt AND noticEndDt")
	List<Notices> findAllActiveNotices();
}
