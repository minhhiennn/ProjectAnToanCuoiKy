package com.projectcuoiky.atbm.repository;

import com.projectcuoiky.atbm.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {

    boolean existsByEmailAndPassword(String email,String password);

}
