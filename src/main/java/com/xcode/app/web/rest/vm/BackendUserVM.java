package com.xcode.app.web.rest.vm;

import com.xcode.app.domain.BaseEntity;
import com.xcode.app.domain.Menu;
import com.xcode.app.domain.Permission;
import com.xcode.app.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class BackendUserVM extends BaseEntity implements Serializable {

    private Long id;
    private String username;
    private String realName;
    private String email;
    private String phone;
    private Boolean enabled;
    private LocalDateTime lastLoginTime;
    private Set<Role> roles = new HashSet<>();
    private Set<Permission> permissions = new HashSet<>();
    private Set<Menu> menus = new HashSet<>();

}
