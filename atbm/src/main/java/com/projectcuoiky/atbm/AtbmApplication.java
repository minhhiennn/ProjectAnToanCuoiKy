package com.projectcuoiky.atbm;

import com.projectcuoiky.atbm.entities.Role;
import com.projectcuoiky.atbm.entities.User;
import com.projectcuoiky.atbm.repository.RoleRepository;
import com.projectcuoiky.atbm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@SpringBootApplication
public class AtbmApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(AtbmApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		 // Roles
//		 if (roleRepository.findByName("ROLE_ADMIN") == null) {
//		 	roleRepository.save(new Role("ROLE_ADMIN"));
//		 }
//
//		 if (roleRepository.findByName("ROLE_MEMBER") == null) {
//		 	roleRepository.save(new Role("ROLE_USER"));
//		 }

//		 // Admin account
//		 if (userRepository.findByEmail("admin@gmail.com") == null) {
//		 	User admin = new User();
//		 	admin.setEmail("admin@gmail.com");
//		 	admin.setPassword(passwordEncoder.encode("123456"));
//		 	HashSet<Role> roles = new HashSet<>();
//		 	roles.add(roleRepository.findByName("ROLE_ADMIN"));
//		 	roles.add(roleRepository.findByName("ROLE_USER"));
//		 	admin.setRoles(roles);
//		 	userRepository.save(admin);
//		 }
//
//		 // Member account
//		 if (userRepository.findByEmail("member@gmail.com") == null) {
//		 	User user = new User();
//		 	user.setEmail("member@gmail.com");
//		 	user.setPassword(passwordEncoder.encode("123456"));
//		 	HashSet<Role> roles = new HashSet<>();
//		 	roles.add(roleRepository.findByName("ROLE_USER"));
//		 	user.setRoles(roles);
//		 	userRepository.save(user);
//		 }

	}
	
}
