package com.xcode.app.security;

import com.xcode.app.domain.BackendUser;
import com.xcode.app.repository.BackendUserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class BackendUserDetailsService implements UserDetailsService {

    private final BackendUserRepository backendUserRepository;

    public BackendUserDetailsService(BackendUserRepository backendUserRepository) {
        this.backendUserRepository = backendUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BackendUser backendUser = backendUserRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("不存在当前用户: " + username));

        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));

        return new User(
            backendUser.getUsername(),
            backendUser.getPassword(),
            authorities
        );
    }


}
