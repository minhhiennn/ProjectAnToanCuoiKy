package com.projectcuoiky.atbm.service;

import com.projectcuoiky.atbm.entities.Role;
import com.projectcuoiky.atbm.entities.User;
import com.projectcuoiky.atbm.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service("userDetailsService")
public class UsersService implements UserDetailsService {

    private final UserRepository userRepository;

    public UsersService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String e) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(e);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        Set<Role> roles = user.getRoles();
        for (Role role : roles) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), grantedAuthorities);
    }
//    private List<GrantedAuthority> getAuthorities(boolean isAdmin){
//
//        List<GrantedAuthority> authorityList = new ArrayList<GrantedAuthority>(2);
//        authorityList.add(new SimpleGrantedAuthority("USER_ROLE"));
//        if(isAdmin){
//            authorityList.add(new SimpleGrantedAuthority("ADMIN_ROLE"));
//        }
//        return authorityList;
//
//    }
}
