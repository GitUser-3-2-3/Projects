package com.project.bookbackend.book;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.bookbackend.common.BaseEntity;
import com.project.bookbackend.feedback.Feedback;
import com.project.bookbackend.records.BookTransactionHistory;
import com.project.bookbackend.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Book extends BaseEntity {

    private String title;
    private String authorName;
    private String isbn;
    private String synopsis;

    private String bookCover;

    private boolean isArchived;
    private boolean isShareable;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToMany(mappedBy = "book")
    @JsonManagedReference
    private List<Feedback> feedbacks;

    @OneToMany(mappedBy = "book")
    @JsonManagedReference
    private List<BookTransactionHistory> histories;
}








