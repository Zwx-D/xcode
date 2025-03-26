package com.xcode.app.security.jwt;

import com.xcode.app.config.SystemConfig;
import com.xcode.app.domain.BackendUser;
import com.xcode.app.repository.BackendUserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class TokenProvider {

    private static final String AUTHORITIES_KEY = "auth";
    private final Key key;
    private final long tokenValidityInMilliseconds;
    private final BackendUserRepository backendUserRepository;

    public TokenProvider(SystemConfig systemConfig, BackendUserRepository backendUserRepository) {
        this.backendUserRepository = backendUserRepository;
        byte[] keyBytes = Decoders.BASE64.decode(systemConfig.getJwt().getSecret());
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.tokenValidityInMilliseconds = systemConfig.getJwt().getExpirationMs();
    }

    public String createToken(Authentication authentication) {
        String authorities = authentication.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(","));

        long now = (new Date()).getTime();
        Date validity = new Date(now + this.tokenValidityInMilliseconds);
        BackendUser backendUser = backendUserRepository.findByUsername(authentication.getName())
            .orElseThrow(() -> new UsernameNotFoundException("不存在当前用户: " + authentication.getName()));
        return Jwts.builder()
            .setSubject(authentication.getName())
            .claim("realName",backendUser.getRealName())
            .claim(AUTHORITIES_KEY, authorities)
            .signWith(key, SignatureAlgorithm.HS512)
            .setExpiration(validity)
            .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser()
            .setSigningKey(key)
            .parseClaimsJws(token)
            .getBody();

        Collection<? extends GrantedAuthority> authorities =
            Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        User principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(authToken);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
