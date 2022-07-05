package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Order;
import com.example.demo.repositories.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepo;
	
	public Order createOrder(Order order) {
		return orderRepo.save(order);
		
	}
	
	public List<Order> allOrders(){
		return orderRepo.findAll();
	}
	
	public Order updateOrder(Order Order) {
		return orderRepo.save(Order);
	}
	
	public void deleteOrder(Long id) {
		orderRepo.deleteById(id);
	}
	
	public Optional<Order> findOne(Long id) {
		Optional<Order> order = orderRepo.findById(id);
		return order;
	}
	
	public void deleteAll() {
		orderRepo.deleteAll();
	}
	
	public List<Order> findOrdersByOrderNumber(String phone){
		return orderRepo.findOrdersByOrderNumber(phone);
	}
	
	public Optional<Order> findByOrderNumber(String orderNumber) {
		Optional<Order> order = orderRepo.findByOrderNumber(orderNumber);
		return order;
	}
}
