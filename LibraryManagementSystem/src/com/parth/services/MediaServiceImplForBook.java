package com.parth.services;

import com.parth.models.Book;
import com.parth.utils.MediaI;

import java.util.List;
import java.util.Scanner;

import static com.parth.services.MediaService.listBuilder;

public class MediaServiceImplForBook {

    private MediaServiceImplForBook() {
        throw new IllegalStateException("Utility Class");
    }

    private static Book bookDetails(Scanner sc) {
        System.out.println("Enter book Title: ");
        String title = sc.nextLine();

        System.out.println("Enter book Author: ");
        String author = sc.nextLine();

        System.out.println("Enter edition: ");
        Integer edition = sc.nextInt();

        System.out.println("Enter publication year: ");
        Integer year = sc.nextInt();

        System.out.println("Enter reviews: ");
        Double reviews = sc.nextDouble();
        sc.nextLine();

        return new Book.Builder(title, year).author(author)
            .edition(edition).reviews(reviews)
            .build();
    }

    public static void addBook(MediaService<MediaI> media, Scanner sc) {
        Book book = bookDetails(sc);
        media.addMedia(book);
    }

    public static void removeBook(MediaService<MediaI> media, Scanner sc) {
        System.out.println("Enter Book Title: ");
        String title = sc.nextLine();
        System.out.println("Enter Publication Year: ");
        Integer year = sc.nextInt();

        MediaI bookToSearch = media.searchMedia(title, year);

        if (bookToSearch != null) {
            media.removeMedia(bookToSearch);
        } else {
            System.out.println("Book not found.");
        }

    }

    public static void searchBook(MediaService<MediaI> media, Scanner sc) {
        System.out.println("Enter Book Title: ");
        String title = sc.nextLine();
        System.out.println("Enter Publication Year: ");
        Integer year = sc.nextInt();

        MediaI bookToSearch = media.searchMedia(title, year);

        if (bookToSearch != null) {
            System.out.println("Found: " + bookToSearch);
        } else {
            System.out.println("Book not found.");
        }
    }

    public static void advancedSearch(MediaService<MediaI> media, Scanner sc) {
        System.out.println("Enter Keyword");
        String keyword = sc.nextLine();

        List<MediaI> foundBooks = media.advancedSearch(keyword);

        if (foundBooks != null) {
            System.out.println("Books found: " + foundBooks);
        } else {
            System.out.println("No results");
        }
    }

    public static void bookList(MediaService<MediaI> media) {
        List<? extends MediaI> bookList = media.mediaList(Book.class);
        listBuilder(bookList);
    }
}
