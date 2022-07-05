package com.example.demo.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;


import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="products")
public class Product {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotEmpty(message="product name is required!")
    private String productName;
	
	
	@NotNull
    private double price;
	
	@NotEmpty(message="Description is required!")
    private String description;
	
	
	
	@NotEmpty(message="Image1 is required!")
    private String image1;
	
	
	@NotEmpty(message="Image2 is required!")
    private String image2;
	
	@NotNull
    private boolean bestSeller;
	
	public boolean isBestSeller() {
		return bestSeller;
	}
	public void setBestSeller(boolean bestSeller) {
		this.bestSeller = bestSeller;
	}
	@Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;
    
    @OneToMany(mappedBy="product", fetch = FetchType.LAZY)
    private List<Cart> Carts;
    
    @OneToMany(mappedBy="product", fetch = FetchType.LAZY)
    private List<Order> Orders;
    
    public List<Cart> getCarts() {
		return Carts;
	}
	public List<Order> getOrders() {
		return Orders;
	}
	public void setOrders(List<Order> orders) {
		Orders = orders;
	}
	public void setCarts(List<Cart> carts) {
		Carts = carts;
	}
	public Product(){
    	
    }
    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
    
    

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage1() {
		return image1;
	}

	public void setImage1(String image1) {
		this.image1 = image1;
	}

	public String getImage2() {
		return image2;
	}

	public void setImage2(String image2) {
		this.image2 = image2;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
	@PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }


}
