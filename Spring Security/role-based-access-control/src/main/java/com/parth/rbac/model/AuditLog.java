package com.parth.rbac.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class AuditLog {

   @Id
   @GeneratedValue
   private Integer id;

   @Column(nullable = false)
   private Integer adminId;

   @Column(nullable = false)
   private Integer userId;

   @Column(nullable = false)
   private String oldRole;

   @Column(nullable = false)
   private String newRole;

   @CreatedDate
   @Column(nullable = false, updatable = false)
   private LocalDateTime timestamp;

   @Column(nullable = false)
   private String action;
}
