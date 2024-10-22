package com.project.bookbackend.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.bookbackend.book.Book;
import com.project.bookbackend.common.BaseEntity;
import com.project.bookbackend.records.BookTransactionHistory;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@SuperBuilder
@Table(name = "_user")
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User extends BaseEntity implements UserDetails, Principal {

    private String firstname;
    private String lastname;

    private LocalDate dateOfBirth;

    @Email(message = "Invalid Email Format.")
    @Column(unique = true)
    private String userEmail;
    private String password;

    private boolean accountLocked;
    private boolean accountEnabled;

    @JsonBackReference
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

    @OneToMany(mappedBy = "owner")
    @JsonManagedReference
    private List<Book> books;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<BookTransactionHistory> histories;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
            .map(role -> new SimpleGrantedAuthority(role.getName()))
            .toList();
    }

    @Override
    public String getName() {
        return userEmail;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userEmail;
    }

    public String getFullName() {
        return firstname + " " + lastname;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }
}
