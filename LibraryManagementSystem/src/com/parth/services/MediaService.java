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
      T itemToRemove = null;

      for (T mediaItem : mediaItems) {
         if (mediaItem.getTitle().equalsIgnoreCase(media.getTitle()) &&
              mediaItem.getYear().equals(media.getYear())
         ) {
            itemToRemove = mediaItem;
            break;
         }
      }

      if (itemToRemove != null) {
         mediaItems.remove(itemToRemove);
         System.out.println("Item removed: " + itemToRemove);
      } else {
         System.out.println("Item not found");
      }
   }

   // * todo - add a better remove method with advanced search

   public T searchMedia(String title, Integer year) {
      for (T media : mediaItems) {
         if (media.getTitle().equalsIgnoreCase(title) && media.getYear().equals(year)) {
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

   public List<? extends Media> mediaList(Class<? extends Media> mediaType) {
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
