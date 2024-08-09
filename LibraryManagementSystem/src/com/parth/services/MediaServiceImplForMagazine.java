package com.parth.services;

import com.parth.models.Magazine;
import com.parth.utils.MediaI;

import java.util.List;
import java.util.Scanner;

import static com.parth.services.MediaService.listBuilder;

public class MediaServiceImplForMagazine {

    private MediaServiceImplForMagazine() {
        throw new IllegalStateException("Utility Class");
    }

    private static Magazine magazineDetails(Scanner sc) {
        System.out.println("Enter magazine Title: ");
        String title = sc.nextLine();

        System.out.println("Enter magazine publisher: ");
        String publisher = sc.nextLine();

        System.out.println("Enter issueNumber: ");
        Integer issueNumber = sc.nextInt();

        System.out.println("Enter publication year: ");
        Integer year = sc.nextInt();

        System.out.println("Enter reviews: ");
        Double reviews = sc.nextDouble();
        sc.nextLine();

        return new Magazine.Builder(title, publisher, issueNumber)
            .publicationYear(year).reviews(reviews)
            .build();
    }

    public static void addMagazine(MediaService<MediaI> media, Scanner sc) {
        Magazine magazine = magazineDetails(sc);
        media.addMedia(magazine);
    }

    public static void removeMagazine(MediaService<MediaI> media, Scanner sc) {
        System.out.println("Enter Magazine Title: ");
        String title = sc.nextLine();
        System.out.println("Enter Publication Year: ");
        Integer year = sc.nextInt();

        MediaI magazineToSearch = media.searchMedia(title, year);

        if (magazineToSearch != null) {
            media.removeMedia(magazineToSearch);
        } else {
            System.out.println("Magazine not found.");
        }

    }

    public static void searchMagazine(MediaService<MediaI> media, Scanner sc) {
        System.out.println("Enter magazine Title: ");
        String title = sc.nextLine();
        System.out.println("Enter Publication Year: ");
        Integer year = sc.nextInt();

        MediaI magazineToSearch = media.searchMedia(title, year);

        if (magazineToSearch != null) {
            System.out.println("Found: " + magazineToSearch);
        } else {
            System.out.println("Magazine not found.");
        }
    }

    public static void magazineList(MediaService<MediaI> media) {
        List<? extends MediaI> magazineList = media.mediaList(Magazine.class);
        listBuilder(magazineList);
    }
}
