package com.parth.services;

import com.parth.models.Movie;
import com.parth.utils.MediaI;

import java.util.List;
import java.util.Scanner;

import static com.parth.services.MediaService.listBuilder;

public class MediaServiceImplForMovie {

    private MediaServiceImplForMovie() {
        throw new IllegalStateException("Utility Class");
    }

    private static Movie movieDetails(Scanner sc) {
        System.out.println("Enter Movie Title: ");
        String title = sc.nextLine();

        System.out.println("Enter launch year: ");
        Integer year = sc.nextInt();

        System.out.println("Enter length: ");
        Double length = sc.nextDouble();
        sc.nextLine();

        System.out.println("Enter Director: ");
        String director = sc.nextLine();

        System.out.println("Enter reviews: ");
        Double reviews = sc.nextDouble();
        sc.nextLine();

        return new Movie.Builder(title, year, length)
            .director(director).reviews(reviews)
            .build();
    }

    public static void addMovie(MediaService<MediaI> media, Scanner sc) {
        Movie movie = movieDetails(sc);
        media.addMedia(movie);
    }

    public static void removeMovie(MediaService<MediaI> media, Scanner sc) {
        System.out.println("Enter Movie Title: ");
        String title = sc.nextLine();
        System.out.println("Enter Year: ");
        Integer year = sc.nextInt();

        MediaI movieToSearch = media.searchMedia(title, year);

        if (movieToSearch != null) {
            media.removeMedia(movieToSearch);
        } else {
            System.out.println("Movie not found.");
        }
    }

    public static void searchMovie(MediaService<MediaI> media, Scanner sc) {
        System.out.println("Enter Movie Title: ");
        String title = sc.nextLine();
        System.out.println("Enter Launch Year: ");
        Integer year = sc.nextInt();

        MediaI movieToSearch = media.searchMedia(title, year);

        if (movieToSearch != null) {
            System.out.println("Found: " + movieToSearch);
        } else {
            System.out.println("Movie not found.");
        }
    }

    public static void movieList(MediaService<MediaI> media) {
        List<? extends MediaI> movieList = media.mediaList(Movie.class);
        listBuilder(movieList);
    }
}
