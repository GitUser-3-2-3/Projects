package com.parth.models;

import com.parth.utils.Media;

public class Movie implements Media {

   private final String title;
   private final String director;
   private final Integer launchYear;
   private final Double length;
   private final Double reviews;

   public Movie(
        String title, String director, Integer launchYear, Double length, Double reviews
   ) {
      this.title = title;
      this.director = director;
      this.launchYear = launchYear;
      this.length = length;
      this.reviews = reviews;
   }

   public String getTitle() {
      return title;
   }

   @Override
   public String getDirector() {
      return director;
   }

   @Override
   public Integer getYear() {
      return launchYear;
   }

   @Override
   public Double getLength() {
      return length;
   }

   @Override
   public Double getReviews() {
      return reviews;
   }

   @Override
   public String toString() {
      return String.format(
           "Movie[Title = %s, Director = %s, Launch Year = %d, Length = %.2f, Reviews = %.2f",
           title, director, launchYear, length, reviews
      );
   }
}
