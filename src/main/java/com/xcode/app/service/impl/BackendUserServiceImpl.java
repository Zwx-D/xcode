package com.xcode.app.service.impl;

import com.xcode.app.domain.BackendUser;
import com.xcode.app.domain.Menu;
import com.xcode.app.domain.Permission;
import com.xcode.app.domain.Role;
import com.xcode.app.repository.BackendUserRepository;
import com.xcode.app.repository.MenuRepository;
import com.xcode.app.security.jwt.TokenProvider;
import com.xcode.app.service.BackendUserService;
import com.xcode.app.service.exception.UserLoginException;
import com.xcode.app.util.JwtUtils;
import com.xcode.app.web.rest.vm.BackendUserVM;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BackendUserServiceImpl implements BackendUserService {

    @Autowired
    private BackendUserRepository backendUserRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private static final String DEFAULT_PASSWORD = "88888888";

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private TokenProvider tokenProvider;

    @Override
    public String registerUser(BackendUser user) {
        String encodedPassword = passwordEncoder.encode(DEFAULT_PASSWORD);
        user.setPassword(encodedPassword);
        user.setEnabled(true);
        backendUserRepository.save(user);
        return DEFAULT_PASSWORD;
    }

    @Override
    public String loginUser(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                username,
                password
            )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.createToken(authentication);
        return jwt;
    }

}
