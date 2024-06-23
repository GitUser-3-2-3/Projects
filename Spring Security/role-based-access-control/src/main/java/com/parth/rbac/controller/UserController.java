package com.parth.rbac.controller;

import com.parth.rbac.model.User;
import com.parth.rbac.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth/users")
public class UserController {

   private final UserService userService;

   @GetMapping("/{id}")
   public ResponseEntity<?> getUserById(@PathVariable Integer id) {
      Optional<User> user = userService.getUserById(id);

      return user.map(ResponseEntity::ok)
           .orElseGet(() -> ResponseEntity.notFound().build());
   }

   @GetMapping("/email/{email}")
   public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
      Optional<User> user = userService.getUserByEmail(email);

      return user.map(ResponseEntity::ok)
           .orElseGet(() -> ResponseEntity.notFound().build());
   }

   @GetMapping("/getAll")
   public ResponseEntity<Page<User>> getAllUsers(Pageable pageable) {
      Page<User> users = userService.getAllUsers(pageable);

      return ResponseEntity.ok(users);
   }

   @PutMapping("/{adminId}/assign-roles/{userId}")
   public ResponseEntity<?> assignRoles(
        @PathVariable Integer adminId,
        @PathVariable Integer userId,
        @RequestBody Set<String> roles
   ) {
      try {
         User updatedUser = userService.updateUser(adminId, userId, roles);
         return ResponseEntity.ok(updatedUser);
      } catch (IllegalArgumentException e) {
         return ResponseEntity.badRequest().body(e.getMessage());
      } catch (Exception e) {
         return ResponseEntity.status(INTERNAL_SERVER_ERROR)
              .body("An error occurred while assigning roles");
      }
   }
}
