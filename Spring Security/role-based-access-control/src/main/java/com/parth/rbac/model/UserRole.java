package com.parth.rbac.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "user_role")
@EntityListeners(AuditingEntityListener.class)
public class UserRole {

   @Id
   @GeneratedValue
   private Integer id;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "user_id")
   @JsonBackReference
   private User user;


   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "role_id")
   @JsonBackReference
   private Role role;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "assigned_by")
   private User assignedBy;

   @CreatedDate
   @Column(nullable = false, updatable = false)
   private LocalDateTime createdDate;
}
