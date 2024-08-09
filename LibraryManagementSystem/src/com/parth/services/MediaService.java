package com.parth.services;

import com.parth.models.Book;
import com.parth.models.Magazine;
import com.parth.models.Movie;
import com.parth.utils.MediaI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class MediaService<T extends MediaI> {

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

    // add a better remove method with advanced search

    public List<T> advancedSearch(String keyword) {
        if (keyword == null || !keyword.isEmpty()) {
            return Collections.emptyList();
        }

        final String lowerCaseKey = keyword.toLowerCase(Locale.ROOT);
        final ArrayList<T> list = new ArrayList<>(mediaItems.size());

        for (final T media : mediaItems) {
            if (containsMediaKey(media, lowerCaseKey)) {
                list.add(media);
            }
        }

        return list;
    }

    public static boolean containsMediaKey(final MediaI media, String lowerCaseKey) {
        if (media != null) {
            return containsKey(media.getTitle(), lowerCaseKey)
                // This should use media.getSearchKey() polymorphism instead
                || (media instanceof Book && containsKey(media.getAuthor(), lowerCaseKey))
                || (media instanceof Magazine && containsKey(media.getPublisher(), lowerCaseKey))
                || (media instanceof Movie && containsKey(media.getDirector(), lowerCaseKey));
        }
        return false;
    }

    public static boolean containsKey(final String entry, final String lowerCaseKey) {
        return (entry != null
            && !entry.isEmpty()
            && entry.toLowerCase(Locale.ROOT).contains(lowerCaseKey)
        );
    }

    public T searchMedia(final String title, final Integer year) {

        if (year != null && title != null && !title.isEmpty()) {
            for (final T item : mediaItems) {

                if (year.equals(item.getYear())
                    && title.equalsIgnoreCase(item.getTitle())
                ) {
                    return item;
                }
                System.out.println("MediaI does not exists");
            }
        } else {
            System.out.println("Invalid parameters");
        }

        return null;
    }

    public List<? extends MediaI> mediaList(Class<? extends MediaI> mediaType) {
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

    static void listBuilder(List<? extends MediaI> list) {
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
