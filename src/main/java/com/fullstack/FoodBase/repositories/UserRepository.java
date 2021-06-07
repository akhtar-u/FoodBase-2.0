package com.fullstack.FoodBase.repositories;

import com.fullstack.FoodBase.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    User findByEmail(String email);
}
