package com.xcode.app.domain;

import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@MappedSuperclass
public class BaseEntity implements Serializable {

    @Column(nullable = false)
    private String createdBy;

    @Column(nullable = false)
    private LocalDateTime createdTime;

    @Column(nullable = false)
    private String lastModifiedBy;

    @Column(nullable = false)
    private LocalDateTime lastModifiedTime;

    @Column(nullable = false, unique = true)
    private String uuid;

    @PrePersist
    protected void onCreate() {
        this.createdTime = LocalDateTime.now();
        this.lastModifiedTime = this.createdTime;
        this.createdBy = getCurrentUser();
        this.lastModifiedBy = this.createdBy;
        this.uuid = UUID.randomUUID().toString();
    }

    private String getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getName();
        } else {
            return "system";
        }
    }

}
