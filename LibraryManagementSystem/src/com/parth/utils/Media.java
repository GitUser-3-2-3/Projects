package com.parth.utils;

public interface Media {

    String getTitle();

    default String getAuthor() {
        return null;
    }

    default String getDirector() {
        return null;
    }

    default String getPublisher() {
        return null;
    }

    default Integer getYear() {
        return -1;
    }

    default Integer getEdition() {
        return -1;
    }

    default Integer getIssueNumber() {
        return -1;
    }

    default Double getLength() {
        return -1.00;
    }

    default Double getReviews() {
        return -1.00;
    }
}
