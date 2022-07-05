package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Cart;
import com.example.demo.repositories.CartRepository;

@Service
public class CartService {
	
	@Autowired
	private CartRepository cartRepo;
	
	public Cart createCart(Cart cart) {
		return cartRepo.save(cart);
		
	}
	
	public List<Cart> allCarts(){
		return cartRepo.findAll();
	}
	
	public Cart updateCart(Cart cart) {
		return cartRepo.save(cart);
	}
	
	public void deleteCart(Long id) {
		cartRepo.deleteById(id);
	}
	
	public Optional<Cart> findOne(Long id) {
		Optional<Cart> cart = cartRepo.findById(id);
		return cart;
	}
	public void deleteAll() {
		cartRepo.deleteAll();
	}
	
	public List<Cart> findCartsBySessionId(String id){
		return cartRepo.findCartsBySessionId(id);
	}
}
