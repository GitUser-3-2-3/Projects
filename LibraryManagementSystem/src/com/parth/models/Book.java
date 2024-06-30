package com.parth.models;

import com.parth.utils.Media;

public class Book implements Media {

   private final String title;
   private final String author;
   private final Integer edition;
   private final Integer publicationYear;
   private final Double reviews;

   public static class Builder {
      private final String title;
      private final Integer publicationYear;

      private String author = null;
      private Integer edition = 0;
      private Double reviews = 0.00;

      public Builder(String title, Integer publicationYear) {
         this.title = title;
         this.publicationYear = publicationYear;
      }

      public Builder author(String value) {
         author = value;
         return this;
      }

      public Builder edition(Integer value) {
         edition = value;
         return this;
      }

      public Builder reviews(Double value) {
         reviews = value;
         return this;
      }

      public Book build() {
         return new Book(this);
      }
   }

   private Book(Builder builder) {
      title = builder.title;
      author = builder.author;
      edition = builder.edition;
      publicationYear = builder.publicationYear;
      reviews = builder.reviews;
   }

   public String getTitle() {
      return title;
   }

   @Override
   public String getAuthor() {
      return author;
   }

   @Override
   public Integer getEdition() {
      return edition;
   }

   @Override
   public Integer getYear() {
      return publicationYear;
   }

   @Override
   public Double getReviews() {
      return reviews;
   }

   @Override
   public String toString() {
      return String.format(
           "Book[ Title = %s, Author = %s, edition = %d, Publication Year = %d, Reviews = %.2f ]",
           title, author, edition, publicationYear, reviews
      );
   }
}