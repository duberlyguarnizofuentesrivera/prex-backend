package com.duberlyguarnizo.prexbackend.security;

import com.duberlyguarnizo.prexbackend.model.SystemUser;
import com.duberlyguarnizo.prexbackend.repository.SystemUserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    private final SystemUserRepository systemUserRepository;

    public JwtUserDetailsService(SystemUserRepository systemUserRepository) {
        this.systemUserRepository = systemUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) {
        final SystemUser systemUser = systemUserRepository.findBySystemUserUsername(username);
        final String role = systemUser.getSystemUserRole().toString();
        if (systemUser == null) {
            //TODO: catch this exception in the controller
            throw new UsernameNotFoundException("User " + username + " not found");
        }
        return new User(username, systemUser.getSystemUserPassword(), Collections.singletonList(new SimpleGrantedAuthority(role)));
    }
}
