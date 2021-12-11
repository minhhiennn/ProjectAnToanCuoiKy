package com.projectcuoiky.atbm.repository;

import com.projectcuoiky.atbm.entities.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Integer> {

    Role findByName(String name);

}