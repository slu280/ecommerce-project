package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Product;
import com.example.demo.repositories.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepo;
	
	public Product createProduct(Product product) {
		return productRepo.save(product);
		
	}
	
	public List<Product> allProducts(){
		return productRepo.findAll();
	}
	
	public Product updateProduct(Product product) {
		return productRepo.save(product);
	}
	
	public void deleteProduct(Long id) {
		productRepo.deleteById(id);
	}
	
	
	public Optional<Product> findProduct(Long id){
		return productRepo.findById(id);
	}
	
	public List<Product> findProductByBestSeller(boolean bestSeller){
		return productRepo.findProductsByBestSeller(bestSeller);
	}
	
	
}
