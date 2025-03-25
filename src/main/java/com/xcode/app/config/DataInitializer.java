package com.xcode.app.config;

import com.xcode.app.domain.BackendUser;
import com.xcode.app.domain.Role;
import com.xcode.app.repository.BackendUserRepository;
import com.xcode.app.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class DataInitializer implements CommandLineRunner {

    private final BackendUserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final SystemConfig systemConfig;

    public DataInitializer(BackendUserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, SystemConfig systemConfig) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.systemConfig = systemConfig;
    }

    @Override
    @Transactional
    public void run(String... args) {
        UserInitializationConfig userInitializationConfig = systemConfig.getUserInitialization();
        if (userInitializationConfig.getEnabled()) {
            // 创建超级管理员角色
            Role superAdminRole = roleRepository.findOneByRoleName(userInitializationConfig.getRoleName())
                .orElseGet(() -> {
                    Role role = new Role();
                    role.setRoleCode("Admin");
                    role.setRoleName(userInitializationConfig.getRoleName());
                    role.setDescription(userInitializationConfig.getRoleDescription());
                    role.setIsSuperAdmin(true);
                    return roleRepository.save(role);
                });

            // 创建默认管理员用户
            if (userRepository.findByUsername(userInitializationConfig.getUsername()).isEmpty()) {
                BackendUser admin = new BackendUser();
                admin.setUsername(userInitializationConfig.getUsername());
                admin.setPassword(passwordEncoder.encode(userInitializationConfig.getPassword()));
                admin.setRealName(userInitializationConfig.getUsername());
                admin.setEnabled(true);
                Set<Role> roles = new HashSet<>();
                roles.add(superAdminRole);
                admin.setRoles(roles);
                userRepository.save(admin);
            }
        }
    }

}
