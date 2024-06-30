package com.parth;

import com.parth.models.User;
import com.parth.security.AuthenticationSystem;
import com.parth.services.MediaService;
import com.parth.utils.Media;

import java.util.Scanner;

import static com.parth.services.MediaServiceImplForBook.*;
import static com.parth.services.MediaServiceImplForMovie.*;

public class ArchiveApplication {
   public static void main(String[] args) {

      MediaService<Media> media = new MediaService<>();
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
              1. Add Book
              2. Remove Book
              3. Search Book
              4. All Books
              ---------------------------
              5. Add Movie
              6. Remove Movie
              7. Search Movie
              8. All Movies
              ---------------------------
              9. Add Magazine
              10. Remove Magazine
              11. Search Magazine
              12. All Magazines
              ---------------------------
              13. Exit
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
            case 5:
               addMovie(media, sc);
               break;
            case 6:
               removeMovie(media, sc);
               break;
            case 7:
               searchMovie(media, sc);
               break;
            case 8:
               movieList(media);
               break;
            case 13:
               System.out.println("Exiting the System");
            default:
               System.out.println("Invalid choice. Please try again");
         }
      } while (choice != 13);
   }
}