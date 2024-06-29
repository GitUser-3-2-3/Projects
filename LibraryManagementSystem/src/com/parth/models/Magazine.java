package com.parth.models;

import com.parth.utils.Media;

public class Magazine implements Media {

   private final String title;
   private final String publisher;
   private final Integer issueNumber;
   private final Integer publicationYear;
   private final Double reviews;

   public Magazine(
        String title, String publisher, Integer issueNumber, Integer publicationYear, Double reviews
   ) {
      this.title = title;
      this.publisher = publisher;
      this.issueNumber = issueNumber;
      this.publicationYear = publicationYear;
      this.reviews = reviews;
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
      return String.format(
           "Magazine[ Title = %s, Publisher = %s, Issue Number = %d, Publication Year = %d, Reviews = %.2f ]",
           title, publisher, issueNumber, publicationYear, reviews
      );
   }
}
