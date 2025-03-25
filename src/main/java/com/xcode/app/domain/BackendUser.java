package com.xcode.app.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * 后台用户
 */
@Data
@Entity
@Table(name = "backend_users")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class BackendUser extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String realName;

    @Column
    private String email;

    @Column
    private String phone;

    @Column
    private Boolean enabled;

    @Column
    private LocalDateTime lastLoginTime;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "backend_user_roles",
        joinColumns = @JoinColumn(name = "backend_user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

}
