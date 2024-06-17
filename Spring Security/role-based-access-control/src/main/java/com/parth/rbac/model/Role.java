package com.parth.rbac.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Role {

   @Id
   @GeneratedValue
   private Integer id;

   @Column(unique = true)
   private String name;

   @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
   private Set<UserRole> userRoles;

   @CreatedDate
   @Column(nullable = false, updatable = false)
   private LocalDateTime createdDate;

   @LastModifiedDate
   @Column(insertable = false)
   private LocalDateTime lastModifiedAt;
}
