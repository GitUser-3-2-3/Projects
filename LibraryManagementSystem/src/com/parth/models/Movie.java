package com.parth.models;

import com.parth.utils.Media;

public class Movie implements Media {

    private final String title;
    private final String director;
    private final Integer launchYear;
    private final Double length;
    private final Double reviews;

    public static class Builder {

        private final String title;
        private final Integer launchYear;
        private final Double length;

        private String director = null;
        private Double reviews = 0.00;

        public Builder(String title, Integer launchYear, Double length) {
            this.title = title;
            this.launchYear = launchYear;
            this.length = length;
        }

        public Builder director(String value) {
            director = value;
            return this;
        }

        public Builder reviews(Double value) {
            reviews = value;
            return this;
        }

        public Movie build() {
            return new Movie(this);
        }
    }

    private Movie(Builder builder) {
        title = builder.title;
        launchYear = builder.launchYear;
        length = builder.length;
        director = builder.director;
        reviews = builder.reviews;
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
        String movieDetails = String.format(
            "Movie[Title = %s, Director = %s, Launch Year = %d, Length = %.2f, Reviews = %.2f",
            title, director, launchYear, length, reviews
        );
        return movieDetails + System.lineSeparator();
    }
}
