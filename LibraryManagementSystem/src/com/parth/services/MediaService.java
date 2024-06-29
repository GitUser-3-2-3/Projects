package com.parth.services;

import com.parth.utils.Media;

import java.util.ArrayList;
import java.util.Collections;
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

   public void removeMedia(T media) {
      if (mediaItems.remove(media)) {
         System.out.println(media.getTitle() + " has been removed");
      } else {
         System.out.println(media.getTitle() + " was not found");
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

/*
   public void listMedia() {
      if (mediaItems.isEmpty()) {
         System.out.println("Archive is empty");
      } else {
         for (T media : mediaItems) {
            System.out.println(media);
         }
      }
   }
*/

   public List<T> mediaList(Class<T> mediaType) {
      List<T> filteredMedia = new ArrayList<>();

      if (mediaItems.isEmpty()) {
         System.out.println("Archive is empty");
         return Collections.emptyList();
      } else {
         for (T media : mediaItems) {
            if (mediaType.isInstance(media)) {
               filteredMedia.add(media);
            }
         }
      }
      return filteredMedia;
   }
}
