package com.parth.services;

import com.parth.models.Book;
import com.parth.utils.Media;

import java.util.List;
import java.util.Scanner;

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

   public static void addBook(MediaService<Media> media, Scanner sc) {
      Book book = bookDetails(sc);
      media.addMedia(book);
   }

   public static void removeBook(MediaService<Media> media, Scanner sc) {
      System.out.println("Enter Book Title: ");
      String title = sc.nextLine();
      System.out.println("Enter Publication Year: ");
      Integer year = sc.nextInt();

      Media bookToSearch = media.searchMedia(title, year);

      if (bookToSearch != null) {
         media.removeMedia(bookToSearch);
      } else {
         System.out.println("Book not found.");
      }

   }

   public static void searchBook(MediaService<Media> media, Scanner sc) {
      System.out.println("Enter Book Title: ");
      String title = sc.nextLine();
      System.out.println("Enter Publication Year: ");
      Integer year = sc.nextInt();

      Media bookToSearch = media.searchMedia(title, year);

      if (bookToSearch != null) {
         System.out.println("Found: " + bookToSearch);
      } else {
         System.out.println("Book not found.");
      }
   }

   public static void bookList(MediaService<Media> media) {
      List<? extends Media> bookList = media.mediaList(Book.class);

      if (bookList.isEmpty()) {
         System.out.println("[]");
      } else {
         StringBuilder sb = new StringBuilder();
         sb.append("[");
         for (int i = 0; i < bookList.size(); i++) {
            sb.append(bookList.get(i).toString().trim());
            if (i < bookList.size() - 1) {
               sb.append(", \n");
            }
         }
         sb.append("]");
         System.out.println(sb);
      }
   }
}