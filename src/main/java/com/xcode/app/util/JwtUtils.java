package com.xcode.app.util;

import com.xcode.app.config.SystemConfig;
import com.xcode.app.domain.Permission;
import com.xcode.app.web.rest.vm.BackendUserVM;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtUtils {

    @Autowired
    private SystemConfig systemConfig;

    private Key getSecretKey() {
        return Keys.hmacShaKeyFor(systemConfig.getJwt().getSecret().getBytes());
    }

    public String generateJwtToken(BackendUserVM userVM) {
        List<String> permissions = userVM.getPermissions().stream()
            .map(Permission::getPermissionName)
            .collect(Collectors.toList());

        return Jwts.builder()
            .setSubject(userVM.getUsername())
            .claim("id", userVM.getId())
            .claim("permissions", permissions)
            .setIssuedAt(new Date())
            .setExpiration(new Date((new Date()).getTime() + systemConfig.getJwt().getExpirationMs()))
            .signWith(getSecretKey(), SignatureAlgorithm.HS512)
            .compact();
    }


    public static void main(String[] args) {
        // 生成符合 HS512 算法要求的密钥
        Key key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS512);
        // 将密钥编码为 Base64 字符串，方便存储和使用
        String encodedKey = Base64.getEncoder().encodeToString(key.getEncoded());
        System.out.println("生成的 HS512 密钥（Base64 编码）: " + encodedKey);
    }

}
