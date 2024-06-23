package com.parth.rbac.service;

import com.parth.rbac.model.AuditLog;
import com.parth.rbac.model.Role;
import com.parth.rbac.model.User;
import com.parth.rbac.model.UserRole;
import com.parth.rbac.repository.AuditLogRepository;
import com.parth.rbac.repository.RoleRepository;
import com.parth.rbac.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

   private final UserRepository userRepository;
   private final RoleRepository roleRepository;
   private final AuditLogRepository auditLogRepository;

   public Optional<User> getUserById(Integer id) {
      return Optional.ofNullable(userRepository.findById(id)
           .orElseThrow(() -> new RuntimeException("User not found")));
   }

   public Optional<User> getUserByEmail(String email) {
      return Optional.ofNullable(userRepository.findByEmail(email)
           .orElseThrow(() -> new RuntimeException("User not found")));
   }

   public Page<User> getAllUsers(Pageable pageable) {
      return userRepository.findAll(pageable);
   }

   @Transactional
   public User updateUser(Integer adminId, Integer userId, Set<String> roles) {
      User user = userRepository.findById(userId)
           .orElseThrow(() -> new RuntimeException("User not found"));

      Set<UserRole> oldUserRoles = user.getUserRoles();
      Set<UserRole> newUserRoles = new HashSet<>();

      for (String roleName : roles) {
         Role role = roleRepository.findByName(roleName)
              .orElseThrow(() -> new RuntimeException("Role not found"));

         UserRole userRole = new UserRole();
         userRole.setUser(user);
         userRole.setRole(role);
         newUserRoles.add(userRole);
      }

      user.setUserRoles(newUserRoles);
      User updatedUser = userRepository.save(user);

      for (UserRole oldUserRole : oldUserRoles) {

         if (!newUserRoles.contains(oldUserRole)) {
            AuditLog auditLog = AuditLog.builder()
                 .adminId(adminId).userId(userId)
                 .oldRole(oldUserRole.getRole().getName())
                 .newRole(null)
                 .action("REMOVE_ROLE")
                 .build();

            auditLogRepository.save(auditLog);
         }
      }

      for (UserRole newUserRole : newUserRoles) {

         if (!oldUserRoles.contains(newUserRole)) {
            AuditLog auditLog = AuditLog.builder()
                 .adminId(adminId).userId(userId)
                 .oldRole(null)
                 .newRole(newUserRole.getRole().getName())
                 .action("ADD_ROLE")
                 .build();

            auditLogRepository.save(auditLog);
         }
      }

      return updatedUser;
   }
}
