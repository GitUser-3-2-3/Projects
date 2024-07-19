package com.parth.security;

import com.parth.models.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AuthenticationSystem {

    private final Map<String, User> users;

    public AuthenticationSystem() {
        users = new HashMap<>();
        users.put("admin",
            new User.Builder("parth@gmail.com", "pass1")
                .username("parth").build()
        );
        users.put("user",
            new User.Builder("juhi@gmail.com", "pass2")
                .username("juhi").build()
        );
    }

    public User login(Scanner sc) {
        System.out.println("Enter your role: ");
        String role = sc.nextLine();

        System.out.println("Enter your password: ");
        String password = sc.nextLine();

        User user = users.get(role);
        if (user != null && user.authenticate(password)) {
            System.out.println("Login successful.");
            return user;
        } else {
            System.out.println("Invalid username or password.");
            return null;
        }
    }
}
