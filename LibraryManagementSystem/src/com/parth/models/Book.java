package com.parth.models;

import com.parth.utils.Media;

public class Book implements Media {

   private final String title;
   private final String author;
   private final Integer edition;
   private final Integer publicationYear;
   private final Double reviews;

   public Book(
        String title, String author, Integer edition, Integer publicationYear, Double reviews
   ) {
      this.title = title;
      this.author = author;
      this.edition = edition;
      this.publicationYear = publicationYear;
      this.reviews = reviews;
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