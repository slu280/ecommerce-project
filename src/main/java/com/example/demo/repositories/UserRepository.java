package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.User;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository  extends CrudRepository<User, Long> {
	Optional<User> findByEmail(String email);
	List<User> findAll();

}