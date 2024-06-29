package com.parth.services;

import com.parth.utils.Media;

import java.util.ArrayList;
import java.util.List;

public class MediaService<T extends Media> {

   private final List<T> mediaItems;

   public MediaService() {
      this.mediaItems = new ArrayList<>();
   }

   public void addMedia(T media) {
      mediaItems.add(media);
      System.out.println(media.getTitle() + " has been added to the archives");
   }

   public void removeMedia(String title) {
      for (T media : mediaItems) {
         if (media.getTitle().equalsIgnoreCase(title)) {
            if (mediaItems.remove(media)) {
               System.out.println(media.getTitle() + " has been removed");
            } else {
               System.out.println(media.getTitle() + " was not found");
            }
         }
      }
   }

   // ? add a better remove method with advanced search

   public T searchMedia(String title) {
      for (T media : mediaItems) {
         if (media.getTitle().equalsIgnoreCase(title)) {
            return media;
         }
      }
      return null;
   }

   public void listMedia() {
      if (mediaItems.isEmpty()) {
         System.out.println("Archive is empty");
      } else {
         for (T media : mediaItems) {
            System.out.println(media);
         }
      }
   }
}
