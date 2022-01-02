package com.projectcuoiky.atbm.service;

import com.projectcuoiky.atbm.entities.CustomUserDetail;
import com.projectcuoiky.atbm.entities.Role;
import com.projectcuoiky.atbm.entities.User;
import com.projectcuoiky.atbm.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service("userDetailsService")
public class UsersService implements UserDetailsService {

    private final UserRepository userRepository;

    public UsersService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public CustomUserDetail loadUserByUsername(String e) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(e);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        Set<Role> roles = user.getRoles();
        for (Role role : roles) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        CustomUserDetail customUserDetail=new CustomUserDetail();
        customUserDetail.setUser(user);
        customUserDetail.setAuthorities(grantedAuthorities);

        return customUserDetail;
    }
}
