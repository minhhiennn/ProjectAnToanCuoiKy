package com.projectcuoiky.atbm.service;

import com.projectcuoiky.atbm.entities.Users;
import com.projectcuoiky.atbm.repository.UsersRepository;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public boolean checkLogin(Users users) {
        boolean bool = this.usersRepository.existsByEmailAndPassword(users.getEmail(), users.getPassword());
        return bool;
    }

}
