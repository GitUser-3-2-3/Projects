package com.parth.rbac.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user")
@EntityListeners(AuditingEntityListener.class)
public class User implements UserDetails {

   @Id
   @GeneratedValue
   private Integer id;

   @NotNull
   @Column(nullable = false)
   private String firstname;
   private String lastname;

   @NotNull
   @Column(unique = true, nullable = false)
   private String email;

   @NotNull
   @Length(min = 8)
   @Column(nullable = false)
   @NotBlank(message = "Password is mandatory")
   private String password;

   private boolean accountLocked;
   private boolean enabled;

   @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
   @JsonManagedReference
   private Set<UserRole> userRoles;

   @CreatedDate
   @Column(nullable = false, updatable = false)
   private LocalDateTime createdDate;

   @LastModifiedDate
   @Column(insertable = false)
   private LocalDateTime lastModifiedDate;

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return userRoles.stream()
           .map(userRole ->
                new SimpleGrantedAuthority(userRole.getRole().getName())
           )
           .collect(Collectors.toList());
   }

   @Override
   public String getUsername() {
      return email;
   }

   @Override
   public boolean isAccountNonLocked() {
      return !accountLocked;
   }

   @Override
   public boolean isEnabled() {
      return enabled;
   }

   public String fullName() {
      return firstname + " " + lastname;
   }
}
