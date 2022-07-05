package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Cart;


import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long>{
	List<Cart> findAll();
	
	Optional<Cart> findById(Long id);
	
	void deleteAll();
	
	List<Cart> findCartsBySessionId(String id);
	
	
}
