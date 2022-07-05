package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Order;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long>{
	List<Order> findAll();
	
	Optional<Order> findById(Long id);
	 
	void deleteAll();
	
	List<Order> findOrdersByOrderNumber(String orderNumber);
	
	Optional<Order> findByOrderNumber(String orderNumber);
}
