package com.projectcuoiky.atbm.repository;

import com.projectcuoiky.atbm.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByEmail(String email);

}
