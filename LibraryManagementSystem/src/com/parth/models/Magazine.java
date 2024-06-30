package com.parth.models;

import com.parth.utils.Media;

public class Magazine implements Media {

   private final String title;
   private final String publisher;
   private final Integer issueNumber;
   private final Integer publicationYear;
   private final Double reviews;

   public static class Builder {

      private final String title;
      private final String publisher;
      private final Integer issueNumber;

      private Integer publicationYear = 0;
      private Double reviews = 0.00;

      public Builder(String title, String publisher, Integer issueNumber) {
         this.title = title;
         this.publisher = publisher;
         this.issueNumber = issueNumber;
      }

      public Builder publicationYear(Integer value) {
         publicationYear = value;
         return this;
      }

      public Builder reviews(Double value) {
         reviews = value;
         return this;
      }

      public Magazine build() {
         return new Magazine(this);
      }
   }

   private Magazine(Builder builder) {
      title = builder.title;
      publisher = builder.publisher;
      issueNumber = builder.issueNumber;
      publicationYear = builder.publicationYear;
      reviews = builder.reviews;
   }

   public String getTitle() {
      return title;
   }

   @Override
   public String getPublisher() {
      return publisher;
   }

   @Override
   public Integer getIssueNumber() {
      return issueNumber;
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
      String magazineDetails = String.format(
           "Magazine[ Title = %s, Publisher = %s, Issue Number = %d, Publication Year = %d, Reviews = %.2f ]",
           title, publisher, issueNumber, publicationYear, reviews
      );
      return magazineDetails + System.lineSeparator();
   }
}
