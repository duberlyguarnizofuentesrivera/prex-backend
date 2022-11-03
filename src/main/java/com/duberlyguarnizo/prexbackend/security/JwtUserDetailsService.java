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
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    private final SystemUserRepository systemUserRepository;

    public JwtUserDetailsService(SystemUserRepository systemUserRepository) {
        this.systemUserRepository = systemUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) {
        final List<SystemUser> systemUser = systemUserRepository.findBySystemUserUsername(username);
        if (systemUser.size() == 1) {
            SystemUser user = systemUser.get(0); //must be only one
            final String role;
            role = user.getSystemUserRole().toString();
            return new User(username, user.getSystemUserPassword(), Collections.singletonList(new SimpleGrantedAuthority(role)));
        } else {
            throw new UsernameNotFoundException("User " + username + " not found");
        }
    }
}
