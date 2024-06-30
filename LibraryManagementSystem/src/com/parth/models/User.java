package com.parth.models;

public class User {

   private final String username;
   private final String email;
   private final String password;

   public static class Builder {

      private String username = null;
      private final String email;
      private final String password;

      public Builder(String email, String password) {
         this.email = email;
         this.password = password;
      }

      public Builder username(String value) {
         username = value;
         return this;
      }

      public User build() {
         return new User(this);
      }
   }

   private User(Builder builder) {
      email = builder.email;
      password = builder.password;
      username = builder.username;
   }

   public String getUsername() {
      return username;
   }

   public String getEmail() {
      return email;
   }

   public boolean authenticate(String password) {
      return this.password.equals(password);
   }
}
