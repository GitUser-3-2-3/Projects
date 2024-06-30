package com.parth;

import com.parth.services.MediaService;
import com.parth.services.MediaServiceImplForBook;
import com.parth.utils.Media;

import java.util.Scanner;

public class ArchiveApplication {
   public static void main(String[] args) {
      MediaService<Media> media = new MediaService<>();
      Scanner sc = new Scanner(System.in);
      int choice;

      do {
         System.out.println("\n" + "Archive Management System");
         System.out.println("1. Add Media");
         System.out.println("2. Remove Media");
         System.out.println("3. Search Media");
         System.out.println("4. List Media");
         System.out.println("5. Exit");
         System.out.println("Enter your choice: ");
         choice = sc.nextInt();
         sc.nextLine();

         switch (choice) {
            case 1:
               MediaServiceImplForBook.addBook(media, sc);
               break;
            case 2:
               MediaServiceImplForBook.removeBook(media, sc);
               break;
            case 3:
               MediaServiceImplForBook.searchBook(media, sc);
               break;
            case 4:
               MediaServiceImplForBook.bookList(media);
               break;
            case 5:
               System.out.println("Exiting the system");
               break;
            default:
               System.out.println("Invalid choice. Please try again");
         }
      } while (choice != 5);
   }
}