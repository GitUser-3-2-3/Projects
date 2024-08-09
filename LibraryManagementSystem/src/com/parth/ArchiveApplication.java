package com.parth;

import com.parth.models.User;
import com.parth.security.AuthenticationSystem;
import com.parth.services.MediaService;
import com.parth.utils.MediaI;

import java.util.Scanner;

import static com.parth.services.MediaServiceImplForBook.*;
import static com.parth.services.MediaServiceImplForMagazine.*;
import static com.parth.services.MediaServiceImplForMovie.*;

public class ArchiveApplication {

    private static void handleBookOptions(MediaService<MediaI> media, Scanner sc) {
        int choice;

        System.out.println("""
            \nBook Options
            --------------
            1. Add
            2. Remove
            3. Search
            4. List
            """);
        System.out.println("Enter your choice: ");
        choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1:
                addBook(media, sc);
                break;
            case 2:
                removeBook(media, sc);
                break;
            case 3:
                searchBook(media, sc);
                break;
            case 4:
                bookList(media);
                break;
            default:
                System.out.println("Invalid choice. Please try again");
        }
    }

    private static void handleMovieOptions(MediaService<MediaI> media, Scanner sc) {
        int choice;

        System.out.println("""
            \nMovie Options
            --------------
            1. Add
            2. Remove
            3. Search
            4. List
            """);
        System.out.println("Enter your choice: ");
        choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1:
                addMovie(media, sc);
                break;
            case 2:
                removeMovie(media, sc);
                break;
            case 3:
                searchMovie(media, sc);
                break;
            case 4:
                movieList(media);
                break;
            default:
                System.out.println("Invalid choice. Please try again");
        }
    }

    private static void handleMagazineOptions(MediaService<MediaI> media, Scanner sc) {
        int choice;

        System.out.println("""
            \nMagazine Options
            --------------
            1. Add
            2. Remove
            3. Search
            4. List
            """);
        System.out.println("Enter your choice: ");
        choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1:
                addMagazine(media, sc);
                break;
            case 2:
                removeMagazine(media, sc);
                break;
            case 3:
                searchMagazine(media, sc);
                break;
            case 4:
                magazineList(media);
                break;
            default:
                System.out.println("Invalid choice. Please try again");
        }
    }

    public static void main(String[] args) {

        MediaService<MediaI> media = new MediaService<>();
        AuthenticationSystem authSystem = new AuthenticationSystem();
        Scanner sc = new Scanner(System.in);

        User loggedInUser = authSystem.login(sc);
        if (loggedInUser == null) {
            System.out.println("Exiting the system");
            return;
        }

        int choice;

        do {
            System.out.println("""
                \nArchive Management System
                ---------------------------
                1. Book
                2. Movie
                3. Magazine
                4. Exit
                """);
            System.out.println("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    handleBookOptions(media, sc);
                    break;
                case 2:
                    handleMovieOptions(media, sc);
                    break;
                case 3:
                    handleMagazineOptions(media, sc);
                    break;
                case 4:
                    System.out.println("Exiting the System");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again");
            }
        } while (choice != 4);
    }
}