package com.farm.dao;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.farm.model.user.User;

public interface UserDao extends MongoRepository<User, String> {
	Optional<User> getUserByEmail(String email);

	Optional<User> getUserById(String id);

	Optional<User> getUserByIdAndPassword(String id, String password);

}
