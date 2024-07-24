package com.parth.services;

import com.parth.models.Book;
import com.parth.models.Magazine;
import com.parth.models.Movie;
import com.parth.utils.Media;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

    public List<T> advancedSearch(String keyword) {
        String lowerCaseKey = keyword.toLowerCase();

        return mediaItems.parallelStream()
            .filter(media -> media.getTitle().toLowerCase().contains(lowerCaseKey)
                || (media instanceof Book && media.getAuthor().toLowerCase().contains(lowerCaseKey))
                || (media instanceof Magazine && media.getPublisher().contains(lowerCaseKey))
                || (media instanceof Movie && media.getDirector().toLowerCase().contains(lowerCaseKey)))
            .toList();
    }

    public T searchMedia(String title, Integer year) {
        Optional<T> optionalT = mediaItems.stream()
            .filter(item -> item.getTitle().equalsIgnoreCase(title) && item.getYear().equals(year))
            .findFirst();

        if (optionalT.isPresent()) {
            return optionalT.get();
        } else {
            System.out.println("Media does not exists");
        }

        return null;
    }

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

    static void listBuilder(List<? extends Media> list) {
        if (list.isEmpty()) {
            System.out.println("[]");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (int i = 0; i < list.size(); i++) {
                sb.append(list.get(i).toString().trim());
                if (i < list.size() - 1) {
                    sb.append(", \n");
                }
            }
            sb.append("]");
            System.out.println(sb);
        }
    }

}
